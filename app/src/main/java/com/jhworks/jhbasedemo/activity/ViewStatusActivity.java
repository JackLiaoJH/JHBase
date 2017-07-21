package com.jhworks.jhbasedemo.activity;

import android.support.v7.widget.Toolbar;

import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbase.base.BaseFragmentActivity;
import com.jhworks.jhbasedemo.BaseChildActivity;
import com.jhworks.jhbasedemo.fragment.ViewStatusFragment;

/**
 * @apiNote
 * @since 2017/7/20
 * <p>
 * author: jacksonliao
 */
public class ViewStatusActivity extends BaseChildActivity {

    @Override
    protected void initToolBar(Toolbar toolbar) {
        super.initToolBar(toolbar);
        toolbar.setTitle(ViewStatusActivity.class.getSimpleName());
    }

    @Override
    protected BaseFragment getFragment() {
        return new ViewStatusFragment();
    }
}
