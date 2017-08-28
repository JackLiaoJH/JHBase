package com.jhworks.jhbasedemo.mvp;

import com.jhworks.jhbase.mvp.BaseView;

/**
 * @apiNote
 * @since 2017/8/28
 * <p>
 * author: 行走的老者
 */
public class MultiMvpDemoContact {
    public interface Presenter {
        void onPresenter1Data();

        void onPresenter2Data();
    }

    public interface View extends BaseView {
        void onPresenter1View(String data);

        void onPresenter2View(String data);
    }
}
