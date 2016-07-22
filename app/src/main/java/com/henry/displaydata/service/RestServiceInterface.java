package com.henry.displaydata.service;

import com.henry.displaydata.model.Person;

import java.util.ArrayList;
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
}
