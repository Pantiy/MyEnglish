package com.pantiy.myenglish.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.pantiy.myenglish.fragment.ResultFragment;
import com.pantiy.myenglish.model.QueryResult;
import com.pantiy.myenglish.model.QueryResultLab;

import java.util.List;

/**
 * MyEnglish
 * com.pantiy.myenglish.adapter
 * Created by Pantiy on 2017/4/6.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class ResultPagerAdapter extends FragmentStatePagerAdapter {

    private String mQuery;

    private Context mContext;

    public ResultPagerAdapter(Context context, FragmentManager fm, String query) {
        super(fm);
        mContext = context;
        mQuery = query;
    }

    @Override
    public Fragment getItem(int position) {
        return ResultFragment.newInstance(mContext, getQueryResult(mQuery));
    }

    @Override
    public int getCount() {
        return 1;
    }

    private QueryResult getQueryResult(String query) {
        List<QueryResult> queryResultList = QueryResultLab.get().getQueryResults();
        QueryResult queryResult = new QueryResult();
        for (int i = 0; i < queryResultList.size(); i++) {
            if (queryResultList.get(i).equals(mQuery)) {
                queryResult = queryResultList.get(i);
            }
        }
        return queryResult;
    }
}
