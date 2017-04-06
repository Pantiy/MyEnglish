package com.pantiy.myenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pantiy.myenglish.R;
import com.pantiy.myenglish.model.QueryResult;
import com.pantiy.myenglish.model.QueryResultLab;

import java.util.List;

/**
 * MyEnglish
 * com.pantiy.myenglish.adapter
 * Created by Pantiy on 2017/4/6.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class HistoryAdapter extends BaseAdapter {

    private List<QueryResult> mQueryResults;

    private Context mContext;

    public HistoryAdapter(Context context) {
        mContext = context;
        init();
    }

    @Override
    public int getCount() {
        return mQueryResults.size();
    }

    @Override
    public Object getItem(int position) {
        return mQueryResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_for_history, parent, false);
            TextView query = (TextView) convertView.findViewById(R.id.query_textView);
            query.setText(mQueryResults.get(position).getQuery());
        }
        return convertView;
    }

    private void init() {
        mQueryResults = QueryResultLab.get().getQueryResults();
    }
}
