package com.henry.displaydata.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Henry.Oforeh on 2016/07/22.
 */
public class RestClient
{


    private static RestServiceInterface REST_CLIENT;
    private static final String REST_SERVICE_URL = "http://demo4012764.mockable.io/";

    static {
        setupRestClient();
    }
    private RestClient() {}

    public static RestServiceInterface getRestClient() {
        return REST_CLIENT;
    }

    public static void setRestClient(RestServiceInterface restClient) {
        REST_CLIENT = restClient;
    }
    private static void setupRestClient()
    {
        Gson gson = new GsonBuilder()
                .create();
         RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(REST_SERVICE_URL)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(new OkHttpClient()))
                .build();
        REST_CLIENT = restAdapter.create(RestServiceInterface.class);
    }
}
