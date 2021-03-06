package com.henry.displaydata.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henry.Oforeh on 2016/07/22.
 */
public class SQLDDL
{
    private SQLDDL() {}

    public static List<String> getSQLTableDropStatements()
    {
        SQLHelper sqlHelper = new SQLiteHelper();
        List<String> statements = new ArrayList<>();
        statements.add(sqlHelper.getTableDropDDL(DataContract.PersonTable.TABLE_NAME));
        statements.add(sqlHelper.getTableDropDDL(DataContract.PersonDetailTable.TABLE_NAME));
        return statements;
    }

    public static List<String> getSQLTableCreateStatements()
    {
        List<String> statements = new ArrayList<>();
        statements.add(getCreatePersonTableDDL());
        statements.add(getCreatePersonDetailTableDDL());
        return statements;
    }

    private static String getCreatePersonTableDDL()
    {
        SQLHelper sqlHelper = new SQLiteHelper();
        List<SQLColumn> columns = new ArrayList<>();
        columns.add(new SQLColumn(DataContract.PersonTable.COLUMN_NAME_ID, sqlHelper.getSQLTypeInteger(),true));
        columns.add(new SQLColumn(DataContract.PersonTable.COLUMN_NAME_FIRST_NAME, sqlHelper.getSQLTypeString()));
        columns.add(new SQLColumn(DataContract.PersonTable.COLUMN_NAME_LAST_NAME, sqlHelper.getSQLTypeString()));
        return sqlHelper.getTableCreateDDL(DataContract.PersonTable.TABLE_NAME, columns);
    }

    private static String getCreatePersonDetailTableDDL()
    {
        SQLHelper sqlHelper = new SQLiteHelper();
        List<SQLColumn> columns = new ArrayList<>();
        columns.add(new SQLColumn(DataContract.PersonDetailTable.COLUMN_NAME_ID, sqlHelper.getSQLTypeInteger()));
        columns.add(new SQLColumn(DataContract.PersonDetailTable.COLUMN_NAME_PERSON_ID, sqlHelper.getSQLTypeInteger(),true));
        columns.add(new SQLColumn(DataContract.PersonDetailTable.COLUMN_NAME_AGE, sqlHelper.getSQLTypeInteger()));
        columns.add(new SQLColumn(DataContract.PersonDetailTable.COLUMN_NAME_COLOR, sqlHelper.getSQLTypeString()));
        return sqlHelper.getTableCreateDDL(DataContract.PersonDetailTable.TABLE_NAME, columns);
    }

}
