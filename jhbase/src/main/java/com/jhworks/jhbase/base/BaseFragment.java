package com.jhworks.jhbase.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jhworks.jhbase.helper.PermissionHelper;
import com.jhworks.jhbase.widget.CommonErrorView;

/**
 * @apiNote 基类 Fragment （TODO 包含权限申请，懒加载，失败界面，无网络界面等一些通用界面）
 * @since 2017/7/17
 * <p>
 * author: jacksonliao
 */
public abstract class BaseFragment extends Fragment
        implements PermissionHelper.OnRequestPermissionCallBack {
    private static final String BUNDLE_DATA = "bundle_data";

    protected Context mContext;
    private FrameLayout mRootView;
    private CommonErrorView mErrorView;
    private View mContentView;

    private PermissionHelper mPermissionHelper;

    private boolean mShowErrorView;


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
        if (mRootView == null) {
            mRootView = new FrameLayout(mContext);

            if (mShowErrorView) {
                mErrorView = new CommonErrorView(mContext);
                mErrorView.setVisibility(View.GONE);
                mRootView.addView(mErrorView);
            }

            if (getLayout() != 0) {
                mContentView = inflater.inflate(getLayout(), container, false);
                mRootView.addView(mContentView, 0);
                initView(mContentView, savedInstanceState);
            }
        }
        return mRootView;
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

    /**
     * 必须在oncreate()方法中调用才有效果
     *
     * @param showErrorView
     */
    public void showErrorView(boolean showErrorView) {
        mShowErrorView = showErrorView;
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

    protected void openActivity(Class clazz) {
        openActivity(clazz, null);
    }

    protected void openActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(mContext, clazz);
        if (bundle != null) {
            intent.putExtra(BUNDLE_DATA, bundle);
        }
        startActivity(intent);
    }

    protected void openActivityForResult(Class clazz, int requestCode) {
        openActivityForResult(clazz, requestCode, null);
    }

    protected void openActivityForResult(Class clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(mContext, clazz);
        if (bundle != null) {
            intent.putExtra(BUNDLE_DATA, bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    protected void showNoNetView() {
        checkErrorViewNull();
        mErrorView.showNoNetView();
        mContentView.setVisibility(View.GONE);
    }

    protected void showNoContentView() {
        checkErrorViewNull();
        mErrorView.showNoContentView();
        mContentView.setVisibility(View.GONE);
    }

    protected void showNetErrorView() {
        checkErrorViewNull();
        mErrorView.showNetErrorView();
        mContentView.setVisibility(View.GONE);
    }

    protected void showLoadingView() {
        checkErrorViewNull();
        mErrorView.showLoadingView();
        mContentView.setVisibility(View.GONE);
    }

    protected void showContentView() {
        checkErrorViewNull();
        mErrorView.setVisibility(View.GONE);
        mContentView.setVisibility(View.VISIBLE);
    }

    protected void setLoadingView(View loadingView) {
        checkErrorViewNull();
        mErrorView.setLoadingView(loadingView);
    }

    protected void setNoNetView(View noNetView) {
        checkErrorViewNull();
        mErrorView.setNoNetView(noNetView);
    }

    protected void setNetErrorView(View netErrorView) {
        checkErrorViewNull();
        mErrorView.setNetErrorView(netErrorView);
    }

    protected void setNoContentView(View noContentView) {
        checkErrorViewNull();
        mErrorView.setNoContentView(noContentView);
    }

    private void checkErrorViewNull() {
        if (mErrorView == null)
            throw new RuntimeException("你必须在Fragment的onCreate()方法中调用showErrorView(true)!!!");
    }


    /**
     * 设置该页面布局文件id
     *
     * @return
     */
    protected abstract int getLayout();

    protected abstract void initView(View rootView, @Nullable Bundle savedInstanceState);

}
