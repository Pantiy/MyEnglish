package com.pantiy.myenglish.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Pantiy on 2017/3/22.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public abstract class BaseFragment extends Fragment {

    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(getLayoutRes(), container, false);

        initViews();
        setupAdapters();
        setupListeners();

        return mView;
    }

    protected abstract void initViews();

    protected abstract void setupAdapters();

    protected abstract void setupListeners();

    protected abstract int getLayoutRes();
}
