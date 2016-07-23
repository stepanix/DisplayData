package com.henry.displaydata.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public void SaveRecord(HashMap<Integer, String> queryValues,String TableName, boolean shouldClear)
    {
        if(shouldClear)
            ClearTable(TableName);

        try{
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            String columnName = "";String valueData="";
            String[] SepData = {"",""};
            String[] ColumnValues = {"",""};
            for (Map.Entry<Integer,String> entry : queryValues.entrySet())
            {
                //split the rawdata that comes from the concatenated values of the web service
                SepData = Utility.split(entry.getValue(), "@@", 0);

                //split the sepdata to get individual columns and their respective values
                for(int x = 0; x < SepData.length; x++)
                {
                    ColumnValues = Utility.split(SepData[x].trim(), "#", 0);
                    columnName = ColumnValues[0];
                    valueData = ColumnValues[1];
                    values.put(columnName, valueData);
                }
                database.insert(TableName, null, values);
            }
            database.close();
        }catch(Exception ex){
        }
    }


    public void ClearTable(String TableName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TableName, null, null);
        db.close();
    }

}
