package com.jhworks.jhbasedemo.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.jhworks.jhbase.base.BaseActivity;
import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbase.base.BaseFragmentActivity;
import com.jhworks.jhbase.utils.ToastUtils;
import com.jhworks.jhbasedemo.R;
import com.jhworks.jhbasedemo.fragment.TestFragment;

/**
 * @apiNote
 * @since 2017/7/17
 * <p>
 * author: jacksonliao
 */
public class TestActivity extends BaseFragmentActivity {
    @Override
    protected BaseFragment getFragment() {
        return new TestFragment();
    }
/*
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fragment, new TestFragment(), TestFragment.class.getSimpleName())
//                .commit();


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ToastUtils.show(this, "请求权限开始....");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
        }
    }*/
}
