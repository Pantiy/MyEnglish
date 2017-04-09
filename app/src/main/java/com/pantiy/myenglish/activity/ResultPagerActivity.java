package com.pantiy.myenglish.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import com.pantiy.myenglish.R;
import com.pantiy.myenglish.adapter.ResultPagerAdapter;
import com.pantiy.myenglish.model.QueryResult;
import com.pantiy.myenglish.model.QueryResultLab;
import java.util.List;

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
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    protected void setupAdapters() {
        ResultPagerAdapter pagerAdapter = new ResultPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        setCurrentPager(mQuery);
    }

    @Override
    protected void setupListeners() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_result_pager;
    }

    private void setCurrentPager(String query) {
        List<QueryResult> queryResultList = QueryResultLab.get(this).getQueryResultList();
        for (int i = 0; i < queryResultList.size(); i++) {
            if (queryResultList.get(i).equals(query)) {
                mViewPager.setCurrentItem(i);
            }
        }
    }
}
