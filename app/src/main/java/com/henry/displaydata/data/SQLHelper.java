package com.henry.displaydata.data;

import java.util.List;

/**
 * Created by Henry.Oforeh on 2016/07/22.
 */
public interface SQLHelper
{
    public String getTableCreateDDL(String tableName, List<SQLColumn> columns);

    public String getTableDropDDL(String tableName);

    public String getSQLTypeString();

    public String getSQLTypeInteger();

    public String getSQLTypeText();
}
