package com.jhworks.jhbase.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

import com.jhworks.jhbase.R;

/**
 * @apiNote
 * @since 2017/7/25
 * <p>
 * author: jacksonliao
 */
public class CommonDialog extends AppCompatDialogFragment {
    private Context mContext;
    private AlertDialog.Builder mBuilder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    public void setDialogBuild(AlertDialog.Builder builder) {
        this.mBuilder = builder;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mBuilder == null)
            mBuilder = new AlertDialog.Builder(mContext);
        return mBuilder.create();
    }

    public static void showWarningDialog(FragmentActivity activity, String warningText) {
        CommonDialog commonDialog = new CommonDialog();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setMessage(warningText)
                .setPositiveButton(R.string.ok, null);
        commonDialog.setDialogBuild(builder);
        commonDialog.setCancelable(false);
        commonDialog.show(activity.getSupportFragmentManager(), activity.getClass().getSimpleName());
    }

}
