package com.pantiy.myenglish.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.pantiy.myenglish.Database.QueryResultDatabase.QueryResultDatabase;
import com.pantiy.myenglish.Database.QueryResultDatabase.QueryResultDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import static com.pantiy.myenglish.Database.QueryResultDatabase.QueryResultDatabase.*;

/**
 * Created by Pantiy on 2017/3/23.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class QueryResultLab {

    private static QueryResultLab sQueryResultLab;

    private SQLiteDatabase mSQLiteDatabase;
    private List<QueryResult> mQueryResults;

    public static QueryResultLab get(Context context) {
        if (sQueryResultLab == null) {
            sQueryResultLab = new QueryResultLab(context);
        }
        return sQueryResultLab;
    }

    private QueryResultLab(Context context){
        mSQLiteDatabase = new QueryResultDatabaseHelper(context).getWritableDatabase();
        mQueryResults = new ArrayList<>();
    }

    public List<QueryResult> getQueryResults() {
        return mQueryResults;
    }

    public void addQueryResult(QueryResult queryResult) {
        ContentValues contentValues = getContentValues(queryResult);
        mSQLiteDatabase.insert(QueryResultDatabase.NAME, null, contentValues);
    }

    private ContentValues getContentValues(QueryResult queryResult) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(Table.QUERY, queryResult.getQuery());
        contentValues.put(Table.TRANSLATION, queryResult.getTranslation());
        contentValues.put(Table.BASIC, queryResult.getBasic());
        contentValues.put(Table.WEB, queryResult.getWeb());

        return contentValues;
    }
}
