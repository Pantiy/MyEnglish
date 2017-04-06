package com.pantiy.myenglish.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.pantiy.myenglish.R;
import com.pantiy.myenglish.adapter.MyFragmentPagerAdapter;
import com.pantiy.myenglish.fragment.QueryFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return QueryFragment.newInstance(this);
    }
}
