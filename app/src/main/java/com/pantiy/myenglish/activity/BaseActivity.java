package com.pantiy.myenglish.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

/**
 * MyEnglish
 * com.pantiy.myenglish.activity
 * Created by Pantiy on 2017/4/6.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        initViews();
        setupAdapters();
        setupListeners();
    }

    protected abstract void initViews();

    protected abstract void setupAdapters();

    protected abstract void setupListeners();

    protected abstract int getLayoutRes();
}
