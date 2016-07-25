package com.henry.displaydata.service;

import android.support.annotation.VisibleForTesting;

import com.henry.displaydata.model.Person;
import com.henry.displaydata.model.PersonDetail;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
/**
 * Created by Henry.Oforeh on 2016/07/22.
 */
public interface RestServiceInterface
{
    @GET("/person")
    ArrayList<Person> GetPersons();


    @GET("/person/{id}")
    public void GetPersonDetail(@Path("id") int id, Callback<PersonDetail> callback);
}
