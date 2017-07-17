package com.jhworks.jhbase.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

/**
 * desc:   log 工具类
 * author: jacksonliao
 * email: 583125288@qq.com
 * date: 17/6/2
 */
public class LogUtils {

    private static boolean IS_DEBUG = false;
    public static String customTagPrefix = "LOG_";

    /**
     * 是否要开启调试模式，调试模式下，debug显示，默认不开启
     *
     * @param debug
     */
    public static void debug(boolean debug) {
        IS_DEBUG = debug;
    }


    private static void i(String tag, String msg) {
        if (IS_DEBUG)
            Log.i(tag, msg);
    }

    private static void w(String tag, String msg) {
        if (IS_DEBUG)
            Log.w(tag, msg);
    }

    private static void d(String tag, String msg) {
        if (IS_DEBUG)
            Log.d(tag, msg);
    }

    private static void e(String tag, String msg) {
        if (IS_DEBUG)
            Log.e(tag, msg);
    }

    public static void w(Class clazz, String format, Object... args) {
        w(clazz.getSimpleName(), String.format(format, args));
    }

    public static void d(Class clazz, String format, Object... args) {
        d(clazz.getSimpleName(), String.format(format, args));
    }

    public static void e(Class clazz, String format, Object... args) {
        e(clazz.getSimpleName(), String.format(format, args));
    }


    @SuppressLint("DefaultLocale")
    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public static void i(String format, Object... args) {
        i(generateTag(getCallerStackTraceElement()), String.format(format, args));
    }

    public static void w(String format, Object... args) {
        w(generateTag(getCallerStackTraceElement()), String.format(format, args));
    }

    public static void d(String format, Object... args) {
        d(generateTag(getCallerStackTraceElement()), String.format(format, args));
    }

    public static void e(String format, Object... args) {
        e(generateTag(getCallerStackTraceElement()), String.format(format, args));
    }

    public static void i(String msg) {
        i(generateTag(getCallerStackTraceElement()), msg);
    }

    public static void w(String msg) {
        w(generateTag(getCallerStackTraceElement()), msg);
    }

    public static void d(String msg) {
        d(generateTag(getCallerStackTraceElement()), msg);
    }

    public static void e(String msg) {
        e(generateTag(getCallerStackTraceElement()), msg);
    }

    public static void i(Throwable tr) {
        i(generateTag(getCallerStackTraceElement()), tr);
    }

    public static void w(Throwable tr) {
        w(generateTag(getCallerStackTraceElement()), tr);
    }

    public static void d(Throwable tr) {
        d(generateTag(getCallerStackTraceElement()), tr);
    }

    public static void e(Throwable tr) {
        e(generateTag(getCallerStackTraceElement()), tr);
    }
}
