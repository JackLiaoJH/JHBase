package com.jhworks.jhbase;

import android.app.Application;

import com.jhworks.jhbase.base.BaseActivity;

import java.util.Iterator;
import java.util.LinkedList;

import cn.jhworks.utilscore.UtilsCore;

/**
 * @apiNote BaseApp
 * @since 2017/7/17
 * <p>
 * author: jacksonliao
 */
public class BaseApp extends Application {
    private static BaseApp sApp;
    private LinkedList<BaseActivity> mActivityList = new LinkedList<>();

    public static BaseApp getApp() {
        return sApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        UtilsCore.get()
                .init(this)
                .debug(BuildConfig.DEBUG);
    }


    public void addActivity(BaseActivity activity) {
        if (!mActivityList.contains(activity)) {
            mActivityList.add(activity);
        }
    }

    public void removeActivity(BaseActivity activity) {
        if (mActivityList.contains(activity)) {
            mActivityList.remove(activity);
        }
    }

    public void removeAll() {
        Iterator<BaseActivity> iterator = mActivityList.iterator();
        for (; iterator.hasNext(); ) {
            iterator.next().finish();
            iterator.remove();
        }
        if (mActivityList.size() > 0)
            mActivityList.clear();
    }

    public LinkedList<BaseActivity> getActivityList() {
        return mActivityList;
    }
}
