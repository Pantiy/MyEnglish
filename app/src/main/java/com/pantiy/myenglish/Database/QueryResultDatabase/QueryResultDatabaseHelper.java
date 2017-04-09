package com.pantiy.myenglish.Database.QueryResultDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * MyEnglish
 * com.pantiy.myenglish.Database.QueryResultDatabase
 * Created by Pantiy on 2017/4/9.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class QueryResultDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "queryResult.db";
    private static final int VERSION = 1;

    public QueryResultDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QueryResultDatabase.getSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
