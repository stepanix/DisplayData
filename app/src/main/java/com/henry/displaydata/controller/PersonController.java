package com.henry.displaydata.controller;

import android.app.Application;
import android.content.Context;

import com.henry.displaydata.data.DbHelper;
import com.henry.displaydata.model.Person;

import java.util.ArrayList;

/**
 * Created by Henry.Oforeh on 2016/07/22.
 */
public class PersonController extends Application
{
      Context context;

      public PersonController(Context context)
      {
        this.context = context;
      }

      public void AddNewPerson(ArrayList<Person> personList)
      {
          try{
              DbHelper dbHelper = new DbHelper(context);
              dbHelper.SaveRecord(personList);
          }catch(Exception ex){

          }
      }

}
