package com.pantiy.myenglish;

import android.net.Uri;

/**
 * Created by Pantiy on 2017/3/22.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class YoudaoClient {

    private static class YoudaoAPI {

        private static final String API_KEY = "727346353";
        private static final String KEY_FROM = "My-English";

        public static String getApiKey() {
            return API_KEY;
        }

        public static String getKeyFrom() {
            return KEY_FROM;
        }
    }

    public static String getUrl(String query) {

        String url = Uri.parse("http://fanyi.youdao.com/openapi.do")
                .buildUpon()
                .appendQueryParameter("keyfrom", YoudaoAPI.getKeyFrom())
                .appendQueryParameter("key", YoudaoAPI.getApiKey())
                .appendQueryParameter("type", "data")
                .appendQueryParameter("doctype", "json")
                .appendQueryParameter("version", "1.1")
                .appendQueryParameter("q", query)
                .build().toString();

        return url;
    }


}
