package com.jhworks.jhbase.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ToastUtils
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-12-9
 */
public class ToastUtils {

    private static Toast sToast = null;

    private ToastUtils(Context context) {
        throw new AssertionError();
    }

/*    @SuppressLint("ShowToast")
    public static void showSingle(Context context, CharSequence text, int duration) {
        if (sToast == null) {
            sToast = Toast.makeText(context.getApplicationContext(), text, duration);
        } else {
            Log.e("liao", "sToast=" + sToast + ",duration=" + sToast.getDuration());
            sToast.setText(text);
            sToast.setDuration(duration);
        }
        sToast.show();
    }

    public static void showSingle(Context context, int resId, int duration) {
        showSingle(context, context.getResources().getText(resId), duration);
    }

    public static void showSingle(Context context, int resId) {
        showSingle(context, resId, Toast.LENGTH_LONG);
    }

    public static void showSingle(Context context, CharSequence text) {
        showSingle(context, text, Toast.LENGTH_LONG);
    }

    public static void showSingle(Context context, int resId, Object... args) {
        showSingle(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void showSingle(Context context, String format, Object... args) {
        showSingle(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void showSingle(Context context, int resId, int duration, Object... args) {
        showSingle(context, String.format(context.getResources().getString(resId), args), duration);
    }

    public static void showSingle(Context context, String format, int duration, Object... args) {
        showSingle(context, String.format(format, args), duration);
    }*/


    public static void show(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        show(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, CharSequence text, int duration) {
        Toast.makeText(context.getApplicationContext(), text, duration).show();
    }

    public static void show(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), duration);
    }

    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }
}
