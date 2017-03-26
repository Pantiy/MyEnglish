package com.pantiy.myenglish;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import java.lang.reflect.Field;

/**
 * Created by Pantiy on 2017/3/22.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class SearchFragment extends BaseFragment {

    private static final String TAG = "SearchFragment";

    private Context mContext;
    private Handler mQueryFinishedHandler;

    private SearchView mQuerySearchView;
    private TextView mResultTextView;
    private TextView mHistoryTextView;

    public static SearchFragment newInstance(Context context) {
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.mContext = context;
        searchFragment.mQueryFinishedHandler = new Handler();
        return searchFragment;
    }

    @Override
    protected void initViews() {
        mQuerySearchView = (SearchView) mView.findViewById(R.id.query_searchView);
        mQuerySearchView.setIconified(false);
        try {
            Class<?> searchViewClass = mQuerySearchView.getClass();
            Field field = searchViewClass.getDeclaredField("mSearchPlate");
            field.setAccessible(true);
            View view = (View) field.get(mQuerySearchView);
            view.setBackgroundColor(Color.TRANSPARENT);
        } catch (NoSuchFieldException ne) {
            Log.e(TAG, " initViews() ", ne);
        } catch (IllegalAccessException iae) {
            Log.e(TAG, " initViews ", iae);
        }
        mResultTextView = (TextView) mView.findViewById(R.id.result_textView);
        mHistoryTextView = (TextView) mView.findViewById(R.id.history_textView);
        Log.i(TAG, YoudaoClient.getUrl("good"));
    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {
        mQuerySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                    new Thread() {
                        @Override
                        public void run() {
                            QueryWord.build(mQueryFinishedHandler, new QueryWord.QueryFinishedListener() {
                                @Override
                                public void onQueryFinished(String result) {
                                    mResultTextView.setText(result);
                                    showHistory();
                                }
                            }).get(query);
                        }
                    }.start();
                mQuerySearchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void showHistory() {
        String history = "";
        if (QueryResultLab.get().getQueryResults().size() > 0){
            for (int i = 0; i < QueryResultLab.get().getQueryResults().size(); i++) {
                history += QueryResultLab.get().getQueryResults().get(i).getQuery() + "\n";
            }
        }
        mHistoryTextView.setText(history);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_query;
    }
}
