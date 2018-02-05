package cn.jhworks.utilscore;

import android.app.Application;
import android.content.Context;

/**
 * <p> 工具类核心初始化,建议在 {@link Application}的 onCreate()初始化,
 * {@code
 * public void onCreate() {
 * super.onCreate();
 * UtilsCore.get().init(this).debug(BuildConfig.DEBUG);
 * ...
 * }
 * </p>
 *
 * @author jiahui
 *         date 2018/2/1
 */
public final class UtilsCore {

    private Context mContext;
    private boolean mDebug;

    private static final class Helper {
        private static final UtilsCore INSTANCE = new UtilsCore();
    }

    public static UtilsCore get() {
        return Helper.INSTANCE;
    }

    public UtilsCore init(Application application) {
        mContext = application.getApplicationContext();
        return this;
    }


    /** 是否开启debug模式 */
    public UtilsCore debug(boolean debug) {
        mDebug = debug;
        return this;
    }

    public Context getContext() {
        return mContext;
    }

    public boolean isDebug() {
        return mDebug;
    }
}
