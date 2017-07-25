package com.jhworks.jhbasedemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbasedemo.R;
import com.jhworks.jhbasedemo.activity.KeyStoreActivity;
import com.jhworks.jhbasedemo.activity.TestActivity;
import com.jhworks.jhbasedemo.activity.ViewStatusActivity;

/**
 * @apiNote
 * @since 2017/7/21
 * <p>
 * author: jacksonliao
 */
public class Main3Fragment extends BaseFragment {


    @Override
    protected int getLayout() {
        return R.layout.fragment_main3;
    }

    @Override
    protected void initView(View rootView, @Nullable Bundle savedInstanceState) {
        rootView.findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast_test();
            }
        });

        rootView.findViewById(R.id.KeyStore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyStore();
            }
        });
        rootView.findViewById(R.id.ViewStatus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewStatus();
            }
        });
    }


    public void toast_test() {
        Bundle bundle = new Bundle();
        bundle.putString("test_data", "hhhhhhhhhhh-------------->");
        openActivity(TestActivity.class, bundle);
    }

    public void keyStore() {
        openActivity(KeyStoreActivity.class);
    }

    public void ViewStatus() {
        openActivity(ViewStatusActivity.class);
    }
}
