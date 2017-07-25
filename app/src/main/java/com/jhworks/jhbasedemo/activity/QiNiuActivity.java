package com.jhworks.jhbasedemo.activity;

import android.support.v7.widget.Toolbar;

import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbase.base.BaseFragmentActivity;
import com.jhworks.jhbasedemo.BaseChildActivity;
import com.jhworks.jhbasedemo.fragment.QiNiuFragment;

/**
 * @apiNote
 * @since 2017/7/25
 * <p>
 * author: jacksonliao
 */
public class QiNiuActivity extends BaseChildActivity {
    @Override
    protected BaseFragment getFragment() {
        return new QiNiuFragment();
    }
}
