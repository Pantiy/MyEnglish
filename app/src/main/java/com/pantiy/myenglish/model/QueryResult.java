package com.pantiy.myenglish.model;

import android.util.Log;

import java.util.List;

/**
 * Created by Pantiy on 2017/3/23.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class QueryResult {

    private int errorCode;
    private String query;
    private String[] translation;
    private Basic basic;
    private List<Web> web;

    private String mQuery;
    private String mTranslation;
    private String mBasic;
    private String mWeb;

    private static String transform(String[] before, String divide, String end) {
        String after = "";
        for (int i = 0; i < before.length; i++) {
            after += before[i] + divide;
        }
        if (after.length() > 2) {
            after = after.substring(0, after.length() - 2) + end;
        }
        return after;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getQuery() {
        if (mQuery == null) {
            mQuery = query;
        }
        return mQuery;
    }

    public void setQuery(String query) {
        mQuery = query;
    }

    public String getTranslation() {
        if (mTranslation == null) {
            mTranslation = transform(translation, "; ", "");
        }
        return mTranslation;
    }

    public void setTranslation(String translation) {
        mTranslation = translation;
    }

    public String getBasic() {
        if (basic == null) {
            return null;
        }
        if (mBasic == null) {
            transform(basic.explains, "\n", "");
        }
        return mBasic;
    }

    public void setBasic(String basic) {
        mBasic = basic;
    }

    public String getWeb() {
        if (web == null) {
            return null;
        }
        if (mWeb == null) {
            mWeb = "";
            for (int i = 0; i < web.size(); i++) {
                mWeb += web.get(i).toString();
            }
        }
        return mWeb;
    }

    public void setWeb(String web) {
        mWeb = web;
    }

    public boolean equals(String query) {
        return this.query.equals(query);
    }

    @Override
    public String toString() {
        if (errorCode != 0) {
            return "无法翻译呦";
        }
        return getQuery() + "\n\n" + getTranslation() + "\n\n" + getBasic() + "\n\n" + getWeb();
    }

    private static class Basic {

        private String[] explains;

    }

    private static class Web {

        private String[] value;
        private String key;

        @Override
        public String toString() {
            return key + "  " + transform(value, ", ", "\n");
        }
    }
}
