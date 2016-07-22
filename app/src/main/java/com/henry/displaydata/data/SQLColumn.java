package com.henry.displaydata.data;

/**
 * Created by Henry.Oforeh on 2016/07/22.
 */
public final class SQLColumn
{
    private String columnName;
    private String columnType;
    private boolean primaryKey = false;

    public SQLColumn(String columnName, String columnType, boolean primaryKey)
    {
        this.columnName = columnName;
        this.columnType = columnType;
        this.primaryKey = primaryKey;
    }

    public SQLColumn(String columnName, String columnType) {
        this(columnName, columnType, false);
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }
}

