package com.pantiy.myenglish.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.pantiy.myenglish.R;
import com.pantiy.myenglish.adapter.ResultPagerAdapter;
import com.pantiy.myenglish.fragment.QueryFragment;

/**
 * MyEnglish
 * com.pantiy.myenglish.activity
 * Created by Pantiy on 2017/4/6.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class ResultPagerActivity extends BaseActivity{

    private static final String EXTRA_QUERY = "extra_query";

    private String mQuery;

    private ViewPager mViewPager;

    public static Intent newInstance(Context packageContext, String query) {
        Intent intent = new Intent(packageContext, ResultPagerActivity.class);
        intent.putExtra(EXTRA_QUERY, query);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mQuery = getIntent().getStringExtra(EXTRA_QUERY);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    protected void setupAdapters() {
        ResultPagerAdapter pagerAdapter = new ResultPagerAdapter(this, getSupportFragmentManager(), mQuery);
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
