package com.pantiy.myenglish.Database.QueryResultDatabase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.pantiy.myenglish.model.QueryResult;

import static com.pantiy.myenglish.Database.QueryResultDatabase.QueryResultDatabase.*;

/**
 * MyEnglish
 * com.pantiy.myenglish.Database.QueryResultDatabase
 * Created by Pantiy on 2017/4/9.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class QueryResultCursorWrapper extends CursorWrapper {

    public QueryResultCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public QueryResult getQueryResult() {

        String query = getString(getColumnIndex(Table.QUERY));
        String translation = getString(getColumnIndex(Table.TRANSLATION));
        String basic = getString(getColumnIndex(Table.BASIC));
        String web = getString(getColumnIndex(Table.WEB));

        QueryResult queryResult = new QueryResult();
        queryResult.setQuery(query);
        queryResult.setTranslation(translation);
        queryResult.setBasic(basic);
        queryResult.setWeb(web);

        return queryResult;
    }
}
