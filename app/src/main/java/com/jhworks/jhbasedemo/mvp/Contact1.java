package com.jhworks.jhbasedemo.mvp;

import com.jhworks.jhbase.mvp.BaseView;

/**
 * @apiNote
 * @since 2017/8/28
 * <p>
 * author: 行走的老者
 */
public class Contact1 {
    public interface Presenter {
        void onData1();
    }

    public interface View extends BaseView {
        void onView1(String data);
    }
}
