package com.henry.displaydata.async;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.henry.displaydata.app.AppConfig;
import com.henry.displaydata.controller.PersonController;
import com.henry.displaydata.model.Person;
import com.henry.displaydata.service.RestServiceInterface;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Henry.Oforeh on 2016/07/23.
 */
public class DownloadTask {
    Context context;

    public DownloadTask(Context context) {
        this.context = context;
    }

    private void DownloadPersons() {
        new AsyncJob.AsyncJobBuilder<ArrayList<Person>>()
                .doInBackground(new AsyncJob.AsyncAction<ArrayList<Person>>() {
                    @Override
                    public ArrayList<Person> doAsync() {
                        try {
                            Gson gson = new GsonBuilder()
                                    .create();

                            RestAdapter restAdapter = new RestAdapter.Builder()
                                    .setEndpoint(AppConfig.REST_SERVICE_URL)
                                    .setConverter(new GsonConverter(gson))
                                    .setClient(new OkClient(new OkHttpClient()))
                                    .build();

                            RestServiceInterface personList = restAdapter.create(RestServiceInterface.class);
                            return personList.GetPersons();
                        } catch (Exception e) {
                            return null;
                        }
                    }
                })
                .doWhenFinished(new AsyncJob.AsyncResultAction<ArrayList<Person>>() {
                    @Override
                    public void onResult(ArrayList<Person> response) {
                        if (response.size() > 0) {
                            PersonController pcontroller = new PersonController(context);
                            pcontroller.AddNewPersons(response);
                        }
                    }
                }).create().start();
    }
}
