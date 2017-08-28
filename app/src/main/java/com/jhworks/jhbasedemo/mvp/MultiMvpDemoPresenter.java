package com.jhworks.jhbasedemo.mvp;

import com.jhworks.jhbase.mvp.BasePresenter;

/**
 * @apiNote
 * @since 2017/8/28
 * <p>
 * author: 行走的老者
 */
public class MultiMvpDemoPresenter extends BasePresenter<MultiMvpDemoContact.View> implements Contact1.View, Contact2.View,MultiMvpDemoContact.Presenter {
    private Presenter1 mPresenter1;
    private Presenter2 mPresenter2;

    public MultiMvpDemoPresenter(MultiMvpDemoContact.View view) {
        super(view);
        mPresenter1 = new Presenter1(this);
        mPresenter2 = new Presenter2(this);
    }

    @Override
    public void errorView() {

    }

    @Override
    public void onView1(String data) {
        mView.onPresenter1View(data);
    }

    @Override
    public void onView2(String data) {
        mView.onPresenter2View(data);
    }


    @Override
    public void onPresenter1Data() {
        mPresenter1.onData1();
    }

    @Override
    public void onPresenter2Data() {
        mPresenter2.onData2();
    }
}
