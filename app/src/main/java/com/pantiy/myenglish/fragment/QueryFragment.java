package com.pantiy.myenglish.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.pantiy.myenglish.activity.ResultPagerActivity;
import com.pantiy.myenglish.adapter.HistoryAdapter;
import com.pantiy.myenglish.model.QueryResult;
import com.pantiy.myenglish.model.QueryResultLab;
import com.pantiy.myenglish.utils.QueryWord;
import com.pantiy.myenglish.R;
import java.lang.reflect.Field;

/**
 * Created by Pantiy on 2017/3/22.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class QueryFragment extends BaseFragment {

    private static final String TAG = "QueryFragment";

    private Handler mQueryFinishedHandler;

    private HistoryAdapter mHistoryAdapter;

    private Context mContext;
    private SearchView mQuerySearchView;
    private ListView mHistoryListView;
    private Button mDeleteAllBtn;

    public static QueryFragment newInstance(Context context) {
        QueryFragment queryFragment = new QueryFragment();
        queryFragment.mContext = context;
        queryFragment.mQueryFinishedHandler = new Handler();
        return queryFragment;
    }

    @Override
    protected void initViews() {
        mQuerySearchView = (SearchView) mView.findViewById(R.id.query_searchView);
        changeSearchViewStyle();
        mHistoryListView = (ListView) mView.findViewById(R.id.history_listView);
        mDeleteAllBtn = (Button) mView.findViewById(R.id.deleteAll_button);
    }

    @Override
    protected void setupAdapters() {
        mHistoryAdapter = new HistoryAdapter(mContext);
        mHistoryListView.setAdapter(mHistoryAdapter);
    }

    @Override
    protected void setupListeners() {

        mQuerySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                queryWord(query);
                mQuerySearchView.clearFocus();
                mQuerySearchView.setQuery(null, false);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mHistoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String query = QueryResultLab.get(mContext).getQueryResultList().get(position).getQuery();
                Intent intent = ResultPagerActivity.newInstance(mContext, query);
                startActivity(intent);
            }
        });

        mHistoryListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) parent.findViewById(R.id.query_textView);
                final String query = textView.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setItems(new String[]{getResources().getString(R.string.delete)},
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        QueryResultLab.get(mContext).deleteQueryResult(query);
                        updateHistoryAdapter();
                        Toast.makeText(mContext, R.string.already_delete, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                mQuerySearchView.clearFocus();
                return true;
            }
        });

        mDeleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QueryResultLab.get(mContext).getQueryResultList().size() == 0) {
                    Toast.makeText(mContext, R.string.empty_history, Toast.LENGTH_SHORT).show();
                    return;
                }
                new AlertDialog.Builder(mContext)
                        .setTitle(R.string.dialog_title)
                        .setMessage(R.string.dialog_message)
                        .setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                QueryResultLab.get(mContext).deleteQueryResultList();
                                updateHistoryAdapter();
                            }
                        })
                        .setNegativeButton(R.string.dialog_negative, null)
                        .show();
                mQuerySearchView.clearFocus();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mQuerySearchView.clearFocus();
    }

    private void queryWord(String query) {
        new QueryWordThread(query).start();
    }

    private void updateHistoryAdapter() {
        mHistoryAdapter = new HistoryAdapter(mContext);
        mHistoryListView.setAdapter(mHistoryAdapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_query;
    }

    private void changeSearchViewStyle() {
        try {
            Class<?> searchViewClass = mQuerySearchView.getClass();
            Field field = searchViewClass.getDeclaredField("mSearchPlate");
            field.setAccessible(true);
            View view = (View) field.get(mQuerySearchView);
            view.setBackgroundColor(Color.TRANSPARENT);
        } catch (NoSuchFieldException ne) {
            Log.e(TAG, " get field ", ne);
        } catch (IllegalAccessException iae) {
            Log.e(TAG, " field.get() ", iae);
        }
    }

    private class QueryWordThread extends Thread {

        private String mQuery;

        private QueryWordThread(String query) {
            super();
            mQuery = query;
        }

        @Override
        public void run() {
            if (mQuery == null) {
                return;
            }
            QueryWord.build(mContext, mQueryFinishedHandler, new QueryWord.QueryFinishedListener() {
                @Override
                public void onQueryFinished(QueryResult queryResult) {
                    updateHistoryAdapter();
                    Intent intent = ResultPagerActivity.newInstance(mContext, mQuery);
                    startActivity(intent);
                }
            }).get(mQuery);
        }
    }
}

