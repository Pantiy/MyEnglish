package com.pantiy.myenglish.activity;

import android.support.v4.app.Fragment;
import com.pantiy.myenglish.fragment.QueryFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return QueryFragment.newInstance(this);
    }
}
