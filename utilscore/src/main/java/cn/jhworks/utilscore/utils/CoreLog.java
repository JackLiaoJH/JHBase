package cn.jhworks.utilscore.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import cn.jhworks.utilscore.UtilsCore;


/**
 * <p> 日志相关类</p>
 *
 * @author jiahui
 *         date 2018/2/1
 */
public class CoreLog {
    private CoreLog() {
        throw new UnsupportedOperationException("can't instantiate CoreLog...");
    }

    public static void info(Object tag, String msg, Object... args) {
        info(tag.getClass().getName(), msg, args);
    }

    public static void info(Object tag, String msg, Throwable throwable) {
        info(tag.getClass().getName(), msg, throwable);
    }

    public static void info(String tag, String msg, Object... args) {
        if (UtilsCore.get().isDebug())
            Log.i(tag, String.format(msg, args));
    }

    public static void info(String tag, String msg, Throwable throwable) {
        if (UtilsCore.get().isDebug())
            Log.i(tag, msg, throwable);
    }


    public static void info(String format, Object... args) {
        i(generateTag(getCallerStackTraceElement()), String.format(format, args));
    }

    public static void warn(String format, Object... args) {
        w(generateTag(getCallerStackTraceElement()), String.format(format, args));
    }

    public static void debug(String format, Object... args) {
        d(generateTag(getCallerStackTraceElement()), String.format(format, args));
    }

    public static void error(String format, Object... args) {
        e(generateTag(getCallerStackTraceElement()), String.format(format, args));
    }

    public static void info(Throwable tr) {
        info(generateTag(getCallerStackTraceElement()), tr);
    }

    public static void warn(Throwable tr) {
        warn(generateTag(getCallerStackTraceElement()), tr);
    }

    public static void debug(Throwable tr) {
        debug(generateTag(getCallerStackTraceElement()), tr);
    }

    public static void error(Throwable tr) {
        error(generateTag(getCallerStackTraceElement()), tr);
    }

    private static void i(String tag, String msg) {
        if (UtilsCore.get().isDebug())
            Log.i(tag, msg);
    }

    private static void w(String tag, String msg) {
        if (UtilsCore.get().isDebug())
            Log.w(tag, msg);
    }

    private static void d(String tag, String msg) {
        if (UtilsCore.get().isDebug())
            Log.d(tag, msg);
    }

    private static void e(String tag, String msg) {
        if (UtilsCore.get().isDebug())
            Log.e(tag, msg);
    }

    @SuppressLint("DefaultLocale")
    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        return tag;
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }
}
