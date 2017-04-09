package com.pantiy.myenglish.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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

    private Context mContext;

    public ResultPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return ResultFragment.newInstance(mContext, getQueryResult(position));
    }

    @Override
    public int getCount() {
        return QueryResultLab.get(mContext).getQueryResultList().size();
    }

    private QueryResult getQueryResult(int position) {
        List<QueryResult> queryResultList = QueryResultLab.get(mContext).getQueryResultList();
        String query = queryResultList.get(position).getQuery();
        QueryResult queryResult = new QueryResult();
        for (int i = 0; i < queryResultList.size(); i++) {
            if (queryResultList.get(i).equals(query)) {
                queryResult = queryResultList.get(i);
            }
        }
        return queryResult;
    }
}
