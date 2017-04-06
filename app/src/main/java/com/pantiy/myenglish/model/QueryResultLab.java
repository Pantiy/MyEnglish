package com.pantiy.myenglish.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pantiy on 2017/3/23.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class QueryResultLab {

    private static QueryResultLab sQueryResultLab;

    private List<QueryResult> mQueryResults;

    public static QueryResultLab get() {
        if (sQueryResultLab == null) {
            sQueryResultLab = new QueryResultLab();
        }
        return sQueryResultLab;
    }

    private QueryResultLab(){
        mQueryResults = new ArrayList<>();
    }

    public List<QueryResult> getQueryResults() {
        return mQueryResults;
    }
}
