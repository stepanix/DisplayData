package com.henry.displaydata.data;

import android.provider.BaseColumns;

/**
 * Created by Henry.Oforeh on 2016/07/22.
 */
public class DataContract
{
    public static final int DATABASE_VERSION = 1;

    private DataContract() {}

    public static abstract class PersonTable implements BaseColumns
    {
        public static final String TABLE_NAME = "Person";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_FIRST_NAME = "firstname";
        public static final String COLUMN_NAME_LAST_NAME = "lastname";
    }

    public static abstract class PersonDetailTable implements BaseColumns
    {
        public static final String TABLE_NAME = "PersonDetail";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_PERSON_ID = "person_id";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_COLOR = "favouritecolor";
    }
}
