package com.jhworks.jhbase.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;


import java.util.ArrayList;
import java.util.List;

import cn.jhworks.utilscore.utils.ListUtils;

/**
 * desc:   权限申请辅助
 * author: jacksonliao
 * email: 583125288@qq.com
 * date: 17/5/26
 */
public class PermissionHelper {
    private int mRequestPermissionCode;
    //处理权限
    private List<String> mRequestPermissionList;
    private OnRequestPermissionCallBack mOnRequestPermissionCallBack;


    public PermissionHelper(OnRequestPermissionCallBack onRequestPermissionCallBack) {
        this.mOnRequestPermissionCallBack = onRequestPermissionCallBack;
    }

    /**
     * Activity中请求权限
     *
     * @param permissions
     * @return true：已经获取了所有请求的权限 false：还没有完全获取权限
     */
    public boolean requestPermissions(Activity activity, int requestCode, String... permissions) {
        if (activity == null) return false;
        if (permissions != null && permissions.length > 0) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mRequestPermissionCode = requestCode;

                if (mRequestPermissionList == null)
                    mRequestPermissionList = new ArrayList<>();
                else
                    mRequestPermissionList.clear();

                for (String permission : permissions) {
                    if (hashPermission(activity, permission)) continue;
                    mRequestPermissionList.add(permission);
                }

                if (mRequestPermissionList.size() == 0) {
                    return true;
                } else {
                    ActivityCompat.requestPermissions(activity,
                            mRequestPermissionList.toArray(new String[mRequestPermissionList.size()]),
                            mRequestPermissionCode);
                    return false;
                }
            } else {
                return true;
            }
        }
        return true;
    }

    /**
     * 在Fragment请求权限
     *
     * @param permissions
     * @return true：已经获取了所有请求的权限 false：还没有完全获取权限
     */
    public boolean requestPermissions(Fragment fragment, int requestCode, String... permissions) {
        if (fragment == null) return false;
        if (permissions != null && permissions.length > 0) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mRequestPermissionCode = requestCode;

                if (mRequestPermissionList == null)
                    mRequestPermissionList = new ArrayList<>();
                else
                    mRequestPermissionList.clear();

                for (String permission : permissions) {
                    if (hashPermission(fragment.getContext(), permission)) continue;
                    mRequestPermissionList.add(permission);
                }

                if (mRequestPermissionList.size() == 0) {
                    return true;
                } else {
                    fragment.requestPermissions(
                            mRequestPermissionList.toArray(new String[mRequestPermissionList.size()]),
                            mRequestPermissionCode);
                    return false;
                }
            } else {
                return true;
            }
        }
        return true;
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == mRequestPermissionCode) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED
                        && !ListUtils.isEmpty(mRequestPermissionList)
                        && !TextUtils.isEmpty(permissions[i])
                        && mRequestPermissionList.contains(permissions[i])) {
                    mRequestPermissionList.remove(permissions[i]);
                }
            }

            String[] permissionArray = mRequestPermissionList.toArray(
                    new String[mRequestPermissionList.size()]);

            if (mRequestPermissionList.size() == 0) {
                if (mOnRequestPermissionCallBack != null)
                    mOnRequestPermissionCallBack.requestPermissionSuccess(requestCode, permissionArray);
            } else {
                if (mOnRequestPermissionCallBack != null)
                    mOnRequestPermissionCallBack.requestPermissionFail(requestCode, permissionArray);
            }
        }
    }


    private boolean hashPermission(Context context, String permission) {
        return context != null &&
                ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void removeCallback() {
        mOnRequestPermissionCallBack = null;
    }

    public interface OnRequestPermissionCallBack {
        void requestPermissionSuccess(int requestPermissionCode, String... permissions);

        void requestPermissionFail(int requestPermissionCode, String... permissions);
    }
}
