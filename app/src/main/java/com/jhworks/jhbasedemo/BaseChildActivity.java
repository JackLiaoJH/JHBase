package com.jhworks.jhbasedemo;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbase.base.BaseFragmentActivity;

/**
 * @apiNote
 * @since 2017/7/21
 * <p>
 * author: jacksonliao
 */
public abstract class BaseChildActivity extends BaseFragmentActivity {
    @Override
    protected void initToolBar(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_while);
    }
}
