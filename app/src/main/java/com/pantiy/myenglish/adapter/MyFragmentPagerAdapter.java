package com.pantiy.myenglish.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.pantiy.myenglish.fragment.HistoryFragment;
import com.pantiy.myenglish.fragment.QueryFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * MyEnglish
 * com.pantiy.myenglish.adapter
 * Created by Pantiy on 2017/4/6.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();

    private Context mContext;

    public MyFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        init();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    private void init() {
        if (mFragmentList == null) {
            return;
        }
        QueryFragment queryFragment = QueryFragment.newInstance(mContext);
        mFragmentList.add(0, queryFragment);
        HistoryFragment historyFragment = HistoryFragment.newInstance(mContext);
        mFragmentList.add(1, historyFragment);
    }
}
