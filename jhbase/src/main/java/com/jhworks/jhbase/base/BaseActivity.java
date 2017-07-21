package com.jhworks.jhbase.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jhworks.jhbase.BaseApp;
import com.jhworks.jhbase.utils.LogUtils;

/**
 * @apiNote 基类 activity
 * @since 2017/7/17
 * <p>
 * author: jacksonliao
 */
public class BaseActivity extends AppCompatActivity {
    private static final String BUNDLE_DATA = "bundle_data";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApp.getApp().addActivity(this);
        Bundle extra = getIntent().getBundleExtra(BUNDLE_DATA);
        if (extra != null) {
            initBundleData(extra);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApp.getApp().removeActivity(this);
    }


    protected <T extends View> T getView(int id) {
        try {
            return (T) findViewById(id);
        } catch (ClassCastException ex) {
            LogUtils.e("Could not cast View to concrete class.", ex);
            throw ex;
        }
    }

    protected void openActivity(Class clazz) {
        openActivity(clazz, null);
    }

    protected void openActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtra(BUNDLE_DATA, bundle);
        }
        startActivity(intent);
    }

    protected void openActivityForResult(Class clazz, int requestCode) {
        openActivityForResult(clazz, requestCode, null);
    }

    protected void openActivityForResult(Class clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtra(BUNDLE_DATA, bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 当使用bundle传递数据时，重写该方法，注意bundle一定不等于null
     *
     * @param bundle
     */
    protected void initBundleData(@NonNull Bundle bundle) {

    }
}
