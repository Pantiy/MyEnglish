package com.pantiy.myenglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pantiy.myenglish.fragment.ResultFragment;

/**
 * MyEnglish
 * com.pantiy.myenglish.adapter
 * Created by Pantiy on 2017/4/6.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class ResultPagerAdapter extends FragmentStatePagerAdapter {

    public ResultPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new ResultFragment();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
