package com.jhworks.jhbasedemo.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbase.utils.LogUtils;
import com.jhworks.jhbase.utils.ToastUtils;
import com.jhworks.jhbasedemo.R;

/**
 * @apiNote
 * @since 2017/7/17
 * <p>
 * author: jacksonliao
 */
public class TestFragment extends BaseFragment {
    private AppCompatButton mButton;
    private String mTestData;

   /* @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ToastUtils.show(getContext(),"请求权限开始....");
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
        }
    }*/

    @Override
    protected void initBundleData(@NonNull Bundle bundle) {
        super.initBundleData(bundle);
        mTestData = bundle.getString("test_data", null);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView(View rootView, @Nullable Bundle savedInstanceState) {
        mButton = (AppCompatButton) rootView.findViewById(R.id.request_permission_btn);
        LogUtils.i("mTestData=%s", mTestData);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissionTest();
            }
        });

        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ToastUtils.show(mContext, "请求权限开始....");
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
        }
    }

    public void requestPermissionTest() {
        requestPermission(100, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA);
    }


    @Override
    public void requestPermissionSuccess(int requestPermissionCode, String... permissions) {
        super.requestPermissionSuccess(requestPermissionCode, permissions);
        if (requestPermissionCode == 100) {
            ToastUtils.show(mContext, "Success......");
        }
    }

    @Override
    public void requestPermissionFail(int requestPermissionCode, String... permissions) {
        super.requestPermissionFail(requestPermissionCode, permissions);
        if (requestPermissionCode == 100) {
            ToastUtils.show(mContext, "Fail......");
        }
    }
}
