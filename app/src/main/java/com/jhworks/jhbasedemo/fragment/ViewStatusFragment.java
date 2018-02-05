package com.jhworks.jhbasedemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbase.utils.LogUtils;
import com.jhworks.jhbasedemo.R;
import com.jhworks.jhbasedemo.widget.ErrorView;

import cn.jhworks.utilscore.utils.RandomUtils;

/**
 * @apiNote
 * @since 2017/7/20
 * <p>
 * author: jacksonliao
 */
public class ViewStatusFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showErrorView(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_view_status;
    }

    @Override
    protected void initView(View rootView, @Nullable Bundle savedInstanceState) {
        int random = RandomUtils.getRandom(5);
        LogUtils.i("random=%d", random);
        setNetErrorView(new ErrorView(mContext));
        if (random == 0) {
            showLoadingView();
        } else if (random == 1) {
            showNetErrorView();
        } else if (random == 2) {
            showContentView();
        } else if (random == 3) {
            showNoNetView();
        } else if (random == 4) {
            showNoContentView();
        }

//        showNoNetView();
    }
}
