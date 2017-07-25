package com.jhworks.jhbasedemo.activity;

import android.support.v7.widget.Toolbar;

import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbasedemo.BaseChildActivity;
import com.jhworks.jhbasedemo.fragment.DialogFragment;

/**
 * @apiNote
 * @since 2017/7/25
 * <p>
 * author: jacksonliao
 */
public class DialogActivity extends BaseChildActivity {
    @Override
    protected BaseFragment getFragment() {
        return new DialogFragment();
    }

    @Override
    protected void initToolBar(Toolbar toolbar) {
        super.initToolBar(toolbar);
        toolbar.setTitle("对话框测试");
    }
}
