package com.jhworks.jhbasedemo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbase.utils.ToastUtils;
import com.jhworks.jhbase.widget.CommonDialog;
import com.jhworks.jhbasedemo.R;

/**
 * @apiNote
 * @since 2017/7/25
 * <p>
 * author: jacksonliao
 */
public class DialogFragment extends BaseFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_dialog_test;
    }

    @Override
    protected void initView(View rootView, @Nullable Bundle savedInstanceState) {

        rootView.findViewById(R.id.default_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(mContext);
                textView.setText("自定义内容");
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.show(mContext,"自定义内容点击....");
                    }
                });


                AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                        .setTitle("测试标题")
                        .setMessage("测试内容")
                        .setView(textView)
                        ;

                CommonDialog dialog = new CommonDialog();
                dialog.setDialogBuild(builder);
                dialog.setCancelable(false);

                dialog.show(getChildFragmentManager(), "default_dialog");
//                CommonDialog.showWarningDialog(getActivity(),"警告对话框.....");
            }
        });
    }
}
