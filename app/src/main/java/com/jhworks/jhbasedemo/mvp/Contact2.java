package com.jhworks.jhbasedemo.mvp;

import com.jhworks.jhbase.mvp.BaseView;

/**
 * @apiNote
 * @since 2017/8/28
 * <p>
 * author: 行走的老者
 */
public class Contact2 {
    public interface Presenter {
        void onData2();
    }

    public interface View extends BaseView {
        void onView2(String data);
    }
}
