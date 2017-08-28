package com.jhworks.jhbasedemo.mvp;

import com.jhworks.jhbase.mvp.BasePresenter;

/**
 * @apiNote
 * @since 2017/8/28
 * <p>
 * author: 行走的老者
 */
public class MvpPresenter extends BasePresenter<MvpContact.View> implements MvpContact.Presenter {
    public MvpPresenter(MvpContact.View view) {
        super(view);
    }

    @Override
    public void mvpData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //TODO：注意，这个在真正使用的时候必须是在UI线程里调用，否则，在回调上不能直接更新ui
                mView.onMvpView("加载数据完成......");
            }
        }).start();
    }
}
