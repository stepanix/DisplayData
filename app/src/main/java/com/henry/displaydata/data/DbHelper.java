package com.henry.displaydata.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.henry.displaydata.model.Person;
import com.henry.displaydata.utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Henry.Oforeh on 2016/07/22.
 */
public class DbHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "person.db";
    private Context context;

    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DataContract.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        for (String stmt : SQLDDL.getSQLTableCreateStatements())
        {
           sqLiteDatabase.execSQL(stmt);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        // drop everything and recreate
        for (String stmt : SQLDDL.getSQLTableDropStatements())
        {
           sqLiteDatabase.execSQL(stmt);
        }
        onCreate(sqLiteDatabase);
    }

    public void SaveRecord(String sqlQuery)
    {
       SQLiteDatabase database = this.getWritableDatabase();
       database.execSQL(sqlQuery);
    }

    public Cursor GetRecords(String sqlQuery)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        return cursor;
    }

    public void ClearTable(String TableName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TableName, null, null);
        db.close();
    }

}
