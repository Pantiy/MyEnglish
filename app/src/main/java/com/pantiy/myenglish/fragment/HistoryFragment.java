package com.pantiy.myenglish.fragment;

import android.content.Context;
import android.widget.ListView;
import com.pantiy.myenglish.R;
import com.pantiy.myenglish.adapter.HistoryAdapter;

/**
 * MyEnglish
 * com.pantiy.myenglish.fragment
 * Created by Pantiy on 2017/4/6.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class HistoryFragment extends BaseFragment {

    private Context mContext;
    private ListView mHistoryListView;

    public static HistoryFragment newInstance(Context context) {
        HistoryFragment historyFragment = new HistoryFragment();
        historyFragment.mContext = context;
        return historyFragment;
    }

    @Override
    protected void initViews() {
        mHistoryListView = (ListView) mView.findViewById(R.id.history_listView);
    }

    @Override
    protected void setupAdapters() {
        HistoryAdapter historyAdapter = new HistoryAdapter(getActivity());
        mHistoryListView.setAdapter(historyAdapter);
    }

    @Override
    protected void setupListeners() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_history;
    }

}
