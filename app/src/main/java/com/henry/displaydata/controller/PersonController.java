package com.henry.displaydata.controller;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.henry.displaydata.app.AppConfig;
import com.henry.displaydata.data.DataContract;
import com.henry.displaydata.data.DbHelper;
import com.henry.displaydata.listener.OnDownloadTaskCompleted;
import com.henry.displaydata.model.Person;
import com.henry.displaydata.model.PersonDetail;
import com.henry.displaydata.service.RestServiceInterface;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

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

      public void AddPersonDetail(PersonDetail personDetail)
      {
          try{
              String sqlQuery = "";
              DbHelper dbHelper = new DbHelper(context);
              sqlQuery = "INSERT INTO " +
                      DataContract.PersonDetailTable.TABLE_NAME +
                      " (" + DataContract.PersonDetailTable.COLUMN_NAME_ID +
                      "," + DataContract.PersonDetailTable.COLUMN_NAME_PERSON_ID +
                      "," + DataContract.PersonDetailTable.COLUMN_NAME_AGE +
                      "," + DataContract.PersonDetailTable.COLUMN_NAME_COLOR + ") " +
                      " Values (" +
                      personDetail.getId() + "," +
                      "'" + personDetail.getPersonId() + "'," +
                      "'" + personDetail.getAge() + "'," +
                      "'" + personDetail.getFavouriteColour() + "');";
              dbHelper.SaveRecord(sqlQuery);
          }catch(SQLiteConstraintException ex){

          }
      }

      public void AddPersons(ArrayList<Person> personList,OnDownloadTaskCompleted listener)
      {
          try{
              String sqlQuery = "";
              DbHelper dbHelper = new DbHelper(context);
              for(Person person: personList)
              {
                  sqlQuery = "INSERT INTO " +
                          DataContract.PersonTable.TABLE_NAME +
                          " (" + DataContract.PersonTable.COLUMN_NAME_ID +
                          "," + DataContract.PersonTable.COLUMN_NAME_FIRST_NAME +
                          "," + DataContract.PersonTable.COLUMN_NAME_LAST_NAME + ") " +
                          " Values (" +
                          person.getId() + "," +
                          "'" + person.getFirstName() + "'," +
                          "'" + person.getLastName() + "');";
                  dbHelper.SaveRecord(sqlQuery);
              }
              listener.onPersonDownloadTaskCompleted(personList);
              Toast.makeText(context, "current list updated ", Toast.LENGTH_LONG).show();
          }catch(SQLiteConstraintException ex){
              //Toast.makeText(context, "oops! table insert error, primary key id must be unique " + ex.getMessage(), Toast.LENGTH_LONG).show();
          }
      }

      public List<Person> GetPersonList()
      {
            List<Person> personList = new ArrayList<Person>();
            DbHelper dbHelper = new DbHelper(context);
            Cursor cursor = dbHelper.GetRecords("SELECT * FROM " + DataContract.PersonTable.TABLE_NAME);
            if(cursor.moveToFirst())
            {
                do{
                    personList.add(new Person(cursor.getInt(cursor.getColumnIndex(DataContract.PersonTable.COLUMN_NAME_ID)),
                            cursor.getString(cursor.getColumnIndex(DataContract.PersonTable.COLUMN_NAME_FIRST_NAME)),
                            cursor.getString(cursor.getColumnIndex(DataContract.PersonTable.COLUMN_NAME_LAST_NAME))));
                } while (cursor.moveToNext());

                return personList;
            }
            return null;
      }

      public PersonDetail GetPersonDetail(int personId)
      {
            PersonDetail personDetail = null;
            DbHelper dbHelper = new DbHelper(context);
            Cursor cursor = dbHelper.GetRecords("SELECT * FROM " + DataContract.PersonDetailTable.TABLE_NAME
                    + " WHERE " + DataContract.PersonDetailTable.COLUMN_NAME_PERSON_ID + " = " + personId + "");
            if(cursor.moveToFirst())
            {
                personDetail = (new PersonDetail(cursor.getInt(cursor.getColumnIndex(DataContract.PersonDetailTable.COLUMN_NAME_ID)),
                            cursor.getInt(cursor.getColumnIndex(DataContract.PersonDetailTable.COLUMN_NAME_PERSON_ID)),
                            cursor.getInt(cursor.getColumnIndex(DataContract.PersonDetailTable.COLUMN_NAME_AGE)),
                            cursor.getString(cursor.getColumnIndex(DataContract.PersonDetailTable.COLUMN_NAME_COLOR))));
                return personDetail;
            }
            return null;
      }





}
