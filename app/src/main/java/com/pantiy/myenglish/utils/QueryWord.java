package com.pantiy.myenglish.utils;

import android.os.Handler;
import android.util.Log;
import com.google.gson.Gson;
import com.pantiy.myenglish.model.QueryResult;
import com.pantiy.myenglish.model.QueryResultLab;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

/**
 * Created by Pantiy on 2017/3/22.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class QueryWord {

    private QueryFinishedListener mQueryFinishedListener;
    private Handler mQueryFinishedHandler;

    private static final String TAG = "QueryWord";

    private static OkHttpClient sOkHttpClient = new OkHttpClient();
    private static Gson sGson = new Gson();

    public static QueryWord build(Handler queryFinishedHandler, QueryFinishedListener queryFinishedListener) {
        return new QueryWord(queryFinishedHandler, queryFinishedListener);
    }


    private QueryWord(Handler queryFinishedHandler, QueryFinishedListener queryFinished) {
        mQueryFinishedHandler = queryFinishedHandler;
        mQueryFinishedListener = queryFinished;
    }

    public void get(String query) {

        try {
            Request request = new Request.Builder().url(YoudaoClient.getUrl(query)).build();
            Response response = sOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                final String result = response.body().string();
                mQueryFinishedHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mQueryFinishedListener.onQueryFinished(parseResult(result));
                    }
                });
            }
        } catch (IOException ie) {
            Log.e(TAG, "Failed ", ie);
        }
    }

    private String parseResult(String result) {
        QueryResult queryResult = sGson.fromJson(result, QueryResult.class);
        if (queryResult.getErrorCode() == 0) {
            holdResult(queryResult);
        }
        return sGson.fromJson(result, QueryResult.class).toString();
    }

    private void holdResult(QueryResult queryResult) {
        QueryResultLab queryResultLab = QueryResultLab.get();
        queryResultLab.getQueryResults().add(queryResult);
    }

    public interface QueryFinishedListener {
        void onQueryFinished(String result);
    }
}