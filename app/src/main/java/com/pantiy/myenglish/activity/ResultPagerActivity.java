package com.pantiy.myenglish.activity;

import android.support.v4.view.ViewPager;

import com.pantiy.myenglish.R;
import com.pantiy.myenglish.adapter.ResultPagerAdapter;

/**
 * MyEnglish
 * com.pantiy.myenglish.activity
 * Created by Pantiy on 2017/4/6.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class ResultPagerActivity extends BaseActivity{

    private ViewPager mViewPager;

    @Override
    protected void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    protected void setupAdapters() {
        ResultPagerAdapter pagerAdapter = new ResultPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void setupListeners() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_result_pager;
    }
}
