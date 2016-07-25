package com.henry.displaydata.background;

import android.content.Context;

import android.os.AsyncTask;
import android.widget.Toast;


import com.henry.displaydata.controller.PersonController;
import com.henry.displaydata.listener.OnDownloadTaskCompleted;
import com.henry.displaydata.model.Person;
import com.henry.displaydata.model.PersonDetail;
import com.henry.displaydata.service.RestClient;


import java.util.ArrayList;
import retrofit.Callback;
import retrofit.RetrofitError;


/**
 * Created by Henry.Oforeh on 2016/07/23.
 */
public class DownloadPersonTask extends AsyncTask<Object, Void, ArrayList<Person>>
{
    Context context;
    private OnDownloadTaskCompleted listener = null;
    PersonController pcontroller;

    public DownloadPersonTask()
    {
    }

    public DownloadPersonTask(Context context, OnDownloadTaskCompleted listener)
    {
        this.context = context;
        this.listener = listener;

        if(pcontroller==null)
            pcontroller = new PersonController(context);
    }

    @Override
    protected ArrayList<Person> doInBackground(Object... objects)
    {
        synchronized (this)
        {
          return  DownloadPersons();
        }
    }

    public ArrayList<Person> DownloadPersons()
    {
       return RestClient.getRestClient().GetPersons();
    }

    public void ParsePersonDetail(ArrayList<Person> personList)
    {
        for(Person person: personList)
        {
            DownloadPersonDetail(person.getId());
        }
    }

    private void DownloadPersonDetail(final int personId)
    {
        RestClient.getRestClient().GetPersonDetail(personId,new Callback<PersonDetail>()
        {
            @Override
            public void success(PersonDetail tempPersonDetail, retrofit.client.Response response)
            {
                PersonDetail personDetail = new PersonDetail(tempPersonDetail.getId(),
                        personId,tempPersonDetail.getAge(),tempPersonDetail.getFavouriteColour());
                pcontroller.AddPersonDetail(personDetail);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(context, "oops! person detail download error " + retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onPostExecute(ArrayList<Person> result)
    {
       super.onPostExecute(result);
       if(result.size() > 0)
       {
           pcontroller.AddPersons(result,listener);
           ParsePersonDetail(result);
       }
    }



}
