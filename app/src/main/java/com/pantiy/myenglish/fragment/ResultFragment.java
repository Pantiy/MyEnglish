package com.pantiy.myenglish.fragment;

import android.content.Context;
import android.widget.TextView;

import com.pantiy.myenglish.R;
import com.pantiy.myenglish.model.QueryResult;

/**
 * MyEnglish
 * com.pantiy.myenglish.fragment
 * Created by Pantiy on 2017/4/6.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class ResultFragment extends BaseFragment {

    private QueryResult mQueryResult;

    private Context mContext;
    private TextView mQueryTv;
    private TextView mTranslateTv;

    public static ResultFragment newInstance(Context context, QueryResult queryResult) {
        ResultFragment resultFragment = new ResultFragment();
        resultFragment.init(context, queryResult);
        return resultFragment;
    }

    @Override
    protected void initViews() {
        mQueryTv = (TextView) mView.findViewById(R.id.query_textView);
        mQueryTv.setText(mQueryResult.getQuery());
        mTranslateTv = (TextView) mView.findViewById(R.id.translate_textView);
        mTranslateTv.setText(mQueryResult.toString());
    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_result;
    }

    private void init(Context context, QueryResult queryResult) {
        mContext = context;
        mQueryResult = queryResult;
    }
}
