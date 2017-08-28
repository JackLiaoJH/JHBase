package com.jhworks.jhbasedemo.mvp;

import com.jhworks.jhbase.mvp.BasePresenter;

/**
 * @apiNote
 * @since 2017/8/28
 * <p>
 * author: 行走的老者
 */
public class Presenter2 extends BasePresenter<Contact2.View> implements Contact2.Presenter {
    public Presenter2(Contact2.View view) {
        super(view);
    }

    @Override
    public void onData2() {
        mView.onView2("Presenter2的数据");
    }
}
