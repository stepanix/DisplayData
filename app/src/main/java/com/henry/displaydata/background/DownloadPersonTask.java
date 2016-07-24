package com.henry.displaydata.background;

import android.content.Context;
import android.database.CursorJoiner;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.henry.displaydata.app.AppConfig;
import com.henry.displaydata.controller.PersonController;
import com.henry.displaydata.listener.OnDownloadTaskCompleted;
import com.henry.displaydata.model.Person;
import com.henry.displaydata.model.PersonDetail;
import com.henry.displaydata.service.RestServiceInterface;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

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
        try {
             Gson gson = new GsonBuilder()
                    .create();

             RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(AppConfig.REST_SERVICE_URL)
                    .setConverter(new GsonConverter(gson))
                    .setClient(new OkClient(new OkHttpClient()))
                    .build();

             RestServiceInterface personListService = restAdapter.create(RestServiceInterface.class);
             return personListService.GetPersons();
        } catch (Exception e) {
            Toast.makeText(context, "oops! download error " + e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
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
        Gson gson = new GsonBuilder()
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(AppConfig.REST_SERVICE_URL)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(new OkHttpClient()))
                .build();
        RestServiceInterface msgs = restAdapter.create(RestServiceInterface.class);

        msgs.GetPersonDetail(personId,new Callback<PersonDetail>()
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
