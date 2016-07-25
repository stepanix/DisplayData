package com.henry.displaydata.activity;

import android.app.AlarmManager;

import android.content.res.Configuration;
import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.henry.displaydata.adapter.data.PersonDataAdapter;

import com.henry.displaydata.background.DownloadPersonTask;
import com.henry.displaydata.controller.PersonController;

import com.henry.displaydata.R;

import com.henry.displaydata.listener.OnDownloadTaskCompleted;
import com.henry.displaydata.model.Person;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * An activity representing a list of Persons. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PersonDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class PersonListActivity extends AppCompatActivity implements OnDownloadTaskCompleted
{
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private AlarmManager am;
    PersonDataAdapter adapter;
    List<Person> personList;
    RecyclerView recyclerView;
    PersonController personController;
    LinearLayoutManager layoutManager;
    protected Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);
        setExecutionTask();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        handler = new Handler();
        recyclerView =(RecyclerView) findViewById(R.id.person_list);
        personList = new ArrayList<Person>();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new PersonDataAdapter(personList, recyclerView);
        recyclerView.setAdapter(adapter);

        if(findViewById(R.id.person_detail_container) != null)
        {
          mTwoPane = true;
          adapter.setPaneView(mTwoPane);
        }

        personController = new PersonController(this);
        GetPersonData();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_person_list);
        if(findViewById(R.id.person_detail_container) != null)
        {
          mTwoPane = true;
          adapter.setPaneView(mTwoPane);
        }
    }

    //For unit Test
    public PersonDataAdapter getPersonAdapter(){
        return adapter;
    }

    private void setExecutionTask()
    {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask()
        {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            DownloadPersonTask task = new DownloadPersonTask(PersonListActivity.this,PersonListActivity.this);
                            task.execute();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 60000);
    }

    private void GetPersonData()
    {
        if(personController.GetPersonList() != null)
        {
            for(Person person : personController.GetPersonList())
            {
                personList.add(new Person(person.getId(),
                        person.getFirstName(),
                        person.getLastName()));
            }
            adapter.notifyItemInserted(personList.size());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPersonDownloadTaskCompleted(ArrayList<Person> response)
    {
        GetPersonData();
    }


}
