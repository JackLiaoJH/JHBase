package com.jhworks.jhbase.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jhworks.jhbase.base.BaseFragment;

/**
 * @apiNote Mvp 封装界面处理
 * @since 2017/8/28
 * <p>
 * author: 行走的老者
 */
public abstract class MvpFragment<T extends BasePresenter> extends BaseFragment {
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
            mPresenter = null;
        }
    }

    /** 创建Presenter */
    protected abstract void createPresenter();
}
