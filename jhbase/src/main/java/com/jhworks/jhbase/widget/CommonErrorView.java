package com.jhworks.jhbase.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jhworks.jhbase.R;

/**
 * @apiNote 基础公共界面 （包括网络加载失败，没有网络，加载中，没有数据等这些公共界面），支持定制
 * @since 2017/7/20
 * <p>
 * author: jacksonliao
 */
public class CommonErrorView extends LinearLayout {
    private View mLoadingView;
    private View mNetErrorView;
    private View mNoNetView;
    private View mNoContentView;

    private ImageView mErrorIv;
    private TextView mErrorTv;
    private ProgressBar mProgressBar;

//    private int[] mResId;

    public CommonErrorView(@NonNull Context context) {
        this(context, null);
    }

    public CommonErrorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public CommonErrorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CommonErrorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.common_error_layout, this);
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);

        mErrorIv = (ImageView) findViewById(R.id.error_icon_iv);
        mErrorTv = (TextView) findViewById(R.id.desc_tv);
        mProgressBar = (ProgressBar) findViewById(R.id.loading_view);
    }


    public void setLoadingView(View loadingView) {
        if (loadingView != null) {
            mProgressBar.setVisibility(GONE);
            mLoadingView = loadingView;
            addView(mLoadingView);
            mLoadingView.setVisibility(GONE);
        }
    }

    public void setNoNetView(View noNetView) {
        if (noNetView != null) {
            setErrorVisible(false);
            mNoNetView = noNetView;
            addView(mNoNetView);
            mNoNetView.setVisibility(GONE);
        }
    }


    public void setNetErrorView(View netErrorView) {
        if (netErrorView != null) {
            setErrorVisible(false);
            mNetErrorView = netErrorView;
            addView(mNetErrorView);
            mNetErrorView.setVisibility(GONE);
        }
    }

    public void setNoContentView(View noContentView) {
        if (noContentView != null) {
            setErrorVisible(false);
            mNoContentView = noContentView;
            addView(mNoContentView);
            mNoContentView.setVisibility(GONE);
        }
    }

    public void showLoadingView() {
        setVisibility(VISIBLE);
        setErrorVisible(false);
        showLoading(true);
        showNoNet(false);
        showNetError(false);
        showNoContent(false);
    }

    public void showNoNetView() {
        setVisibility(VISIBLE);
        setErrorVisible(mNoNetView == null);
        showLoading(false);
        showNetError(false);
        showNoContent(false);
        showNoNet(true);
    }

    public void showNetErrorView() {
        setVisibility(VISIBLE);
        showLoading(false);
        setErrorVisible(mNetErrorView == null);
        showNoNet(false);
        showNetError(true);
        showNoContent(false);
    }

    public void showNoContentView() {
        setVisibility(VISIBLE);
        setErrorVisible(mNoContentView == null);
        showLoading(false);
        showNoNet(false);
        showNetError(false);
        showNoContent(true);
    }

    private void showLoading(boolean loading) {
        if (mLoadingView == null) {
            mProgressBar.setVisibility(loading ? VISIBLE : GONE);
        } else {
            mLoadingView.setVisibility(loading ? VISIBLE : GONE);
        }
    }

    private void showNoNet(boolean isShow) {
        if (mNoNetView == null) {
            if (isShow) {
                mErrorIv.setImageResource(R.mipmap.ic_no_net_icon);
                mErrorTv.setText(R.string.error_no_net);
            }
        } else {
            mNoNetView.setVisibility(isShow ? VISIBLE : GONE);
        }
    }

    private void showNetError(boolean isShow) {
        if (mNetErrorView == null) {
            if (isShow) {
                mErrorIv.setImageResource(R.mipmap.ic_net_error_icon);
                mErrorTv.setText(R.string.error_net);
            }
        } else {
            mNetErrorView.setVisibility(isShow ? VISIBLE : GONE);
        }
    }

    private void showNoContent(boolean isShow) {
        if (mNoContentView == null) {
            if (isShow) {
                mErrorIv.setImageResource(R.mipmap.ic_no_content_icon);
                mErrorTv.setText(R.string.error_no_content);
            }
        } else {
            mNoContentView.setVisibility(isShow ? VISIBLE : GONE);
        }
    }

    private void setErrorVisible(boolean visible) {
        mErrorTv.setVisibility(visible ? VISIBLE : GONE);
        mErrorIv.setVisibility(visible ? VISIBLE : GONE);
    }
}
