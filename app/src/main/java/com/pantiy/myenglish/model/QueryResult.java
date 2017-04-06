package com.pantiy.myenglish.model;

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
        return query;
    }

    public String getTranslation() {
        return transform(translation, "; ", "");
    }

    public String getBasic() {
        if (basic == null) {
            return null;
        }
        return transform(basic.explains, "\n", "");
    }

    public String getWeb() {
        if (web == null) {
            return null;
        }
        String webs = "";
        for (int i = 0; i < web.size(); i++) {
            webs += web.get(i).toString();
        }
        return webs;
    }

    @Override
    public String toString() {
        if (errorCode != 0) {
            return "无法翻译呦";
        }
        return getQuery() + "\n\n" + getTranslation() + "\n\n" + getBasic() + "\n\n" + getWeb();
    }

    public boolean equals(QueryResult queryResult) {
        return this.query.equals(queryResult.getQuery());
    }

    private static class Basic {

        private String[] explains;

//        @Override
//        public String toString() {
//            return transform(explains, "\n", "\n");
//        }
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
