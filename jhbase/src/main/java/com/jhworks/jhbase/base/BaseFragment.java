package com.jhworks.jhbase.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jhworks.jhbase.helper.PermissionHelper;

/**
 * @apiNote 基类 Fragment （TODO 包含权限申请，懒加载，失败界面，无网络界面等一些通用界面）
 * @since 2017/7/17
 * <p>
 * author: jacksonliao
 */
public abstract class BaseFragment extends Fragment implements PermissionHelper.OnRequestPermissionCallBack {
    protected Context mContext;
    private PermissionHelper mPermissionHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPermissionHelper = new PermissionHelper(this);
        mContext = getContext();
        Bundle arguments = getArguments();
        if (arguments != null) {
            initBundleData(arguments);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (getLayout() != 0) {
            View view = inflater.inflate(getLayout(), container, false);
            initView(view, savedInstanceState);
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermissionHelper != null)
            mPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onDestroy() {
        if (mPermissionHelper != null) {
            mPermissionHelper.removeCallback();
            mPermissionHelper = null;
        }
        super.onDestroy();
    }

    /**
     * 当使用bundle传递数据时，重写该方法，注意bundle一定不等于null
     *
     * @param bundle
     */
    protected void initBundleData(@NonNull Bundle bundle) {

    }

    /***
     * 请求权限
     * @param requestCode  本次请求吗
     * @param permission 权限集合
     * @return true：有权限  false：无权限
     */
    protected boolean requestPermission(int requestCode, String... permission) {
        return mPermissionHelper.requestPermissions(this, requestCode, permission);
    }

    @Override
    public void requestPermissionSuccess(int requestPermissionCode, String... permissions) {

    }

    @Override
    public void requestPermissionFail(int requestPermissionCode, String... permissions) {

    }

    /**
     * 设置该页面布局文件id
     *
     * @return
     */
    protected abstract int getLayout();

    protected abstract void initView(View rootView, @Nullable Bundle savedInstanceState);

}
