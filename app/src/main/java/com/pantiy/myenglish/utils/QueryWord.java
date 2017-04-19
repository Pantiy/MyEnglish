package com.pantiy.myenglish.utils;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.google.gson.Gson;
import com.pantiy.myenglish.model.QueryResult;
import com.pantiy.myenglish.model.QueryResultLab;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pantiy on 2017/3/22.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class QueryWord {

    private QueryFinishedListener mQueryFinishedListener;
    private Handler mQueryFinishedHandler;
    private Context mContext;

    private static final String TAG = "QueryWord";

//    private static OkHttpClient sOkHttpClient = new OkHttpClient();
    private static Gson sGson = new Gson();

    public static QueryWord build(Context context, Handler queryFinishedHandler,
                                  QueryFinishedListener queryFinishedListener) {
        return new QueryWord(context, queryFinishedHandler, queryFinishedListener);
    }


    private QueryWord(Context context, Handler queryFinishedHandler, QueryFinishedListener queryFinished) {
        mContext = context;
        mQueryFinishedHandler = queryFinishedHandler;
        mQueryFinishedListener = queryFinished;
    }

    public void get(final String query) {
        if (isReady(query) != null) {
            mQueryFinishedHandler.post(new Runnable() {
                @Override
                public void run() {
                    mQueryFinishedListener.onQueryFinished(isReady(query));
                }
            });
            return;
        }
        Network.create(YoudaoClient.getUrl(query), new Network.SuccessListener() {
            @Override
            public void onSuccess(final String result) {
                mQueryFinishedHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mQueryFinishedListener.onQueryFinished(parseResult(result));
                    }
                });
            }
            @Override
            public void onFail() {

            }}).start();
//        try {
//            Request request = new Request.Builder().url(YoudaoClient.getUrl(query)).build();
//            Response response = sOkHttpClient.newCall(request).execute();
//            if (response.isSuccessful()) {
//                final String result = response.body().string();
//                mQueryFinishedHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mQueryFinishedListener.onQueryFinished(parseResult(result));
//                    }
//                });
//            }
//        } catch (IOException ie) {
//            Log.e(TAG, "Failed ", ie);
//        }
    }

    private QueryResult parseResult(String result) {
        QueryResult queryResult = sGson.fromJson(result, QueryResult.class);
        if (queryResult.getErrorCode() == 0) {
            holdResult(queryResult);
        }
        return queryResult;
    }

    private void holdResult(QueryResult queryResult) {
        QueryResultLab queryResultLab = QueryResultLab.get(mContext);
        queryResultLab.addQueryResult(queryResult);
    }

    private QueryResult isReady(String query) {
        List<QueryResult> queryResultList = QueryResultLab.get(mContext).getQueryResultList();
        for (int i = 0; i < queryResultList.size(); i++) {
            if (queryResultList.get(i).equals(query)) {
                return queryResultList.get(i);
            }
        }
        return null;
    }

    public interface QueryFinishedListener {
        void onQueryFinished(QueryResult queryResult);
    }
}