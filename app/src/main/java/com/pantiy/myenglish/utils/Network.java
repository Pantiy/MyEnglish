package com.pantiy.myenglish.utils;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * MyEnglish
 * com.pantiy.myenglish.utils
 * Created by Pantiy on 2017/4/19.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class Network {

    public static final String TAG = "Network";

    private static OkHttpClient sOkHttpClient = new OkHttpClient();

    private Request mRequest;
    private SuccessListener mSuccessListener;

    public static Network create(String url, SuccessListener successListener) {
        return new Network(url, successListener);
    }

    private Network(String url, SuccessListener successListener) {
        mRequest = new Request.Builder().url(url).build();
        mSuccessListener = successListener;
    }

    public void start() {
        try {
            Response response = sOkHttpClient.newCall(mRequest).execute();
            if (response.isSuccessful()) {
                mSuccessListener.onSuccess(response.body().string());
            } else {
                mSuccessListener.onFail();
            }
        } catch (IOException io) {
            Log.e(TAG, "failed ", io);
        }
    }

    interface SuccessListener {
        void onSuccess(String result);
        void onFail();
    }
}
