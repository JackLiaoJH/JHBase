package com.jhworks.jhbasedemo.mvp;

import android.support.v7.widget.Toolbar;

import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbase.base.BaseFragmentActivity;

/**
 * @apiNote
 * @since 2017/8/28
 * <p>
 * author: 行走的老者
 */
public class MvpDemoActivity extends BaseFragmentActivity {
    @Override
    protected BaseFragment getFragment() {
        return new MvpDemoFragment();
    }

    @Override
    protected void initToolBar(Toolbar toolbar) {
        toolbar.setTitle("Mvp 测试");
    }
}
