package com.jhworks.jhbasedemo.mvp;

import com.jhworks.jhbase.mvp.BasePresenter;

/**
 * @apiNote
 * @since 2017/8/28
 * <p>
 * author: 行走的老者
 */
public class Presenter1 extends BasePresenter<Contact1.View> implements Contact1.Presenter {
    public Presenter1(Contact1.View view) {
        super(view);
    }

    @Override
    public void onData1() {
        mView.onView1("Presenter1的数据");
    }
}
