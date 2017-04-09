package com.pantiy.myenglish.Database.QueryResultDatabase;

/**
 * MyEnglish
 * com.pantiy.myenglish.Database.QueryResultDatabase
 * Created by Pantiy on 2017/4/9.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class QueryResultDatabase {

    public static final String NAME = "query_result_list";

    public static String getSql() {
        return "create table " + NAME + "(" +
                Table.QUERY + "," +
                Table.TRANSLATION + "," +
                Table.BASIC + "," +
                Table.WEB + ")";
    }

    public static final class Table {
        public static final String QUERY = "query";
        public static final String TRANSLATION = "translation";
        public static final String BASIC = "basic";
        public static final String WEB = "web";
    }
}
