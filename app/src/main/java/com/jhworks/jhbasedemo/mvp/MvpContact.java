package com.jhworks.jhbasedemo.mvp;

import com.jhworks.jhbase.mvp.BaseView;

/**
 * @apiNote
 * @since 2017/8/28
 * <p>
 * author: 行走的老者
 */
public class MvpContact {
    public interface Presenter {
        void mvpData();
    }

    public interface View extends BaseView {
        void onMvpView(String data);
    }
}
