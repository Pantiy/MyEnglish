package com.pantiy.myenglish.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.pantiy.myenglish.Database.QueryResultDatabase.QueryResultCursorWrapper;
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
    private List<QueryResult> mQueryResultList;

    public static QueryResultLab get(Context context) {
        if (sQueryResultLab == null) {
            sQueryResultLab = new QueryResultLab(context);
        }
        return sQueryResultLab;
    }

    private QueryResultLab(Context context){
        mSQLiteDatabase = new QueryResultDatabaseHelper(context).getWritableDatabase();
        mQueryResultList = new ArrayList<>();
    }

    public List<QueryResult> getQueryResultList() {
        QueryResultCursorWrapper cursorWrapper = getQueryResultCursorWrapper();
        List<QueryResult> queryResultList = new ArrayList<>();
        if (cursorWrapper.getCount() == 0) {
            return queryResultList;
        }
        cursorWrapper.moveToFirst();
        while (!cursorWrapper.isAfterLast()) {
            queryResultList.add(cursorWrapper.getQueryResult());
            cursorWrapper.moveToNext();
        }
        cursorWrapper.close();
        sortQueryResultList(queryResultList);
        return queryResultList;
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

    private QueryResultCursorWrapper getQueryResultCursorWrapper() {

        Cursor cursor = mSQLiteDatabase.query(
                QueryResultDatabase.NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        return new QueryResultCursorWrapper(cursor);
    }

    private void sortQueryResultList(List<QueryResult> queryResultList) {
        for (int i = 0; i < queryResultList.size()/2; i++) {
            int target = queryResultList.size() - 1 - i;
            QueryResult queryResult = queryResultList.get(target);
            queryResultList.set(target, queryResultList.get(i));
            queryResultList.set(i, queryResult);
        }
    }
}
