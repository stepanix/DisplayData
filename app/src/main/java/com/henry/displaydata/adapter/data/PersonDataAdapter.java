package com.henry.displaydata.adapter.data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.henry.displaydata.R;
import com.henry.displaydata.activity.PersonDetailActivity;
import com.henry.displaydata.activity.PersonListActivity;
import com.henry.displaydata.controller.PersonController;
import com.henry.displaydata.fragment.PersonDetailFragment;
import com.henry.displaydata.listener.OnLoadMoreListener;
import com.henry.displaydata.model.Person;
import com.henry.displaydata.model.PersonDetail;

import java.util.List;

/**
 * Created by Henry.Oforeh on 2016/07/23.
 */
public class PersonDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private List<Person> lstPerson;

    private OnLoadMoreListener onLoadMoreListener;

    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private boolean mTwoPane;

    public PersonDataAdapter(List<Person> lstPerson, RecyclerView recyclerView)
    {
        this.lstPerson = lstPerson;

        if (recyclerView.getLayoutManager() instanceof android.support.v7.widget.LinearLayoutManager)
        {
            final android.support.v7.widget.LinearLayoutManager linearLayoutManager = (android.support.v7.widget.LinearLayoutManager) recyclerView
                    .getLayoutManager();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
            {
                @Override
                public void onScrolled(RecyclerView recyclerView,
                                       int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold))
                    {
                        // End has been reached
                        // Do something
                        if (onLoadMoreListener != null)
                        {
                           onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    public void setPaneView(boolean mTwoPane)
    {
        this.mTwoPane = mTwoPane;
    }

    @Override
    public int getItemViewType(int position)
    {
        return lstPerson.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public int getItemCount()
    {
       return lstPerson.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.person_list_content, parent, false);
            vh = new PersonDataViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.progress_item, parent, false);
            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
            Person singlePerson = (Person)lstPerson.get(position);
            ((PersonDataViewHolder) holder).txtFullName.setText(singlePerson.getFirstName() + " " + singlePerson.getLastName());
            ((PersonDataViewHolder) holder).person = singlePerson;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener)
    {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public class PersonDataViewHolder extends RecyclerView.ViewHolder
    {
           TextView txtFullName;
           CardView cardViewFullName;
           Person person;

        public PersonDataViewHolder(View v)
        {
            super(v);
            txtFullName = (TextView) itemView.findViewById(R.id.txtFullName);
            cardViewFullName = (CardView) itemView.findViewById(R.id.cardViewFullName);

            cardViewFullName.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    PersonController personController = new PersonController(v.getContext());
                    PersonDetail detail = personController.GetPersonDetail(person.getId());

                    //PersonDetailFragment.PERSON = person;
                    //PersonDetailFragment.PERSON_DETAIL = detail;
                    //Toast.makeText(v.getContext(), "" + mTwoPane, Toast.LENGTH_LONG).show();

                    if(mTwoPane)
                    {
                      Bundle arguments = new Bundle();
                      arguments.putString(PersonDetailFragment.FAV_COLOR,detail.getFavouriteColour());
                      arguments.putInt(PersonDetailFragment.AGE,detail.getAge());
                      arguments.putString(PersonDetailFragment.FIRST_NAME,person.getFirstName());
                      arguments.putString(PersonDetailFragment.LAST_NAME,person.getLastName());
                      PersonDetailFragment fragment = new PersonDetailFragment();
                      fragment.setArguments(arguments);
                      ((PersonListActivity)v.getContext()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.person_detail_container, fragment)
                                .commit();
                    }else{
                       Context context = v.getContext();
                       Intent intent = new Intent(context, PersonDetailActivity.class);
                       intent.putExtra("person", person);
                       intent.putExtra("detail", detail);
                       context.startActivity(intent);
                    }
                }
            });
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder
    {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v)
        {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }

}
