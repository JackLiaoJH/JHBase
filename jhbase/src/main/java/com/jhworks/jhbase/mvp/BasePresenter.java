package com.jhworks.jhbase.mvp;

/**
 * @apiNote 公共mvp业务处理封装
 * @since 2017/8/28
 * <p>
 * author: 行走的老者
 */
public abstract class BasePresenter<T extends BaseView> {
    protected T mView;

    public BasePresenter(T view) {
        this.mView = view;
    }

    public void onDetach() {
        if (mView != null) {
            mView = null;
        }
    }
}
