package com.jhworks.jhbase.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jhworks.jhbase.R;

/**
 * @apiNote 与Fragment配合使用时使用
 * @since 2017/7/17
 * <p>
 * author: jacksonliao
 */
public abstract class BaseFragmentActivity extends BaseActivity {
    protected BaseFragment mBaseFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseFragment = getFragment();
        setContentView(R.layout.activity_common);
        commitFragment(mBaseFragment);
    }

    @Override
    protected void initBundleData(@NonNull Bundle bundle) {
        super.initBundleData(bundle);
//        mBaseFragment.setArguments(bundle);
    }

    /**
     * 添加Fragment ，当需要改变提交方式时，重写该方法，如commit()变成commitAllowingStateLoss()
     *
     * @param baseFragment
     */
    protected void commitFragment(BaseFragment baseFragment) {
        if (baseFragment == null)
            throw new RuntimeException("getFragment() == null");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, baseFragment, baseFragment.getClass().getSimpleName())
                .commit();
    }

    /** 获取目标Fragment 界面 */
    protected abstract BaseFragment getFragment();
}
