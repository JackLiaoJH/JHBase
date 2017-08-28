package com.jhworks.jhbasedemo.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.jhworks.jhbase.mvp.MvpFragment;
import com.jhworks.jhbase.utils.LogUtils;
import com.jhworks.jhbasedemo.R;

/**
 * @apiNote
 * @since 2017/8/28
 * <p>
 * author: 行走的老者
 */
public class MvpDemoFragment extends MvpFragment<MultiMvpDemoPresenter> implements MvpContact.View, MultiMvpDemoContact.View {
    private TextView mTv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showErrorView(true);
    }

    @Override
    protected void createPresenter() {
//        mPresenter = new MvpPresenter(this);
        mPresenter = new MultiMvpDemoPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mvp;
    }

    @Override
    protected void initView(View rootView, @Nullable Bundle savedInstanceState) {
        mTv = (TextView) rootView.findViewById(R.id.tv);
//        mPresenter.mvpData();
        mPresenter.onPresenter1Data();
        mPresenter.onPresenter2Data();
    }

    @Override
    public void errorView() {
        showNetErrorView();
    }

    @Override
    public void onMvpView(String data) {
        showContentView();
        LogUtils.i("接收到的数据是: %s", data);

//        mTv.setText(data);
    }

    @Override
    public void onPresenter1View(String data) {
        LogUtils.i("从Presenter1接收到的数据是: %s", data);
    }

    @Override
    public void onPresenter2View(String data) {
        LogUtils.i("从Presenter2接收到的数据是: %s", data);
    }
}
