package com.jhworks.jhbasedemo.fragment;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;

import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbasedemo.R;
import com.jhworks.jhbasedemo.activity.KeyStoreActivity;
import com.jhworks.jhbasedemo.activity.TestActivity;
import com.jhworks.jhbasedemo.activity.ViewStatusActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import cn.jhworks.utilscore.utils.CoreLog;
import cn.jhworks.utilscore.utils.SdCardUtils;
import cn.jhworks.utilscore.utils.ZipUtils;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @apiNote
 * @since 2017/7/21
 * <p>
 * author: jacksonliao
 */
public class Main3Fragment extends BaseFragment {
    private RxPermissions mRxPermissions;

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

        mRxPermissions = new RxPermissions(getActivity());
        rootView.findViewById(R.id.zip_compress).setOnClickListener(v -> {
            mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) {
                            //压缩
                            compress();
                        }
                    });

        });

        rootView.findViewById(R.id.zip_decompression).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //解压
            }
        });
    }

    private void compress() {
        Single.create(
                new SingleOnSubscribe<Void>() {
                    @Override
                    public void subscribe(SingleEmitter<Void> e) throws Exception {
                        ZipUtils.unZipFolderByZipFile(SdCardUtils.getSdCardPublicDir(Environment.DIRECTORY_PICTURES) + "/a.zip",
                                SdCardUtils.getSdCardPublicDir(Environment.DIRECTORY_PICTURES));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Void>() {
                    @Override
                    public void accept(Void aVoid) throws Exception {
                        CoreLog.info(mContext, "压缩成功");
                    }
                })
        ;
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
