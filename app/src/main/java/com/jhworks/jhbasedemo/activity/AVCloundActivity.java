package com.jhworks.jhbasedemo.activity;

import android.support.v7.widget.Toolbar;

import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbasedemo.BaseChildActivity;
import com.jhworks.jhbasedemo.fragment.AVCloundFragment;

/**
 * @apiNote
 * @since 2017/7/25
 * <p>
 * author: jacksonliao
 */
public class AVCloundActivity extends BaseChildActivity {
    @Override
    protected BaseFragment getFragment() {
        return new AVCloundFragment();
    }

    @Override
    protected void initToolBar(Toolbar toolbar) {
        super.initToolBar(toolbar);
        toolbar.setTitle("LearnClound 测试");
    }
}
