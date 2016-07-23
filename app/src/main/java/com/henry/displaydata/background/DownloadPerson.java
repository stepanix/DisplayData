package com.henry.displaydata.background;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.henry.displaydata.app.AppConfig;
import com.henry.displaydata.model.Person;
import com.henry.displaydata.service.RestServiceInterface;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Henry.Oforeh on 2016/07/22.
 */
public class DownloadPerson extends AsyncTask<Object, Void, ArrayList<Person>>
{
    Context context;

    public DownloadPerson(Context context)
    {
        this.context = context;
    }

    @Override
    protected ArrayList<Person> doInBackground(Object... objects)
    {
        synchronized (this)
        {
            return DownloadPersonsData();
        }
    }

    private ArrayList<Person> DownloadPersonsData()
    {
        Gson gson = new GsonBuilder()
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(AppConfig.REST_SERVICE_URL)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(new OkHttpClient()))
                .build();

        RestServiceInterface personList = restAdapter.create(RestServiceInterface.class);

        try
        {
            return personList.GetPersons();
        }catch(Exception ex)
        {
            return null;
        }
    }
}

