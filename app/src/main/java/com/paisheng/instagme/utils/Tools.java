package com.paisheng.instagme.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.paisheng.instagme.InstagmeApp;

/**
 * <br> ClassName:   Tools
 * <br> Description: 工具类
 * <br>
 * <br> Author:      zhangweiqiang
 * <br> Date:        2017/8/4 8:42
 */
public class Tools {

    /**
     * 获取渠道信息
     *
     * @return
     */
    public static String getChannel() {
        String channel = SharedPreference.getInstance().getStringValue(SharedPreference.SHARED_PRE_CHANNEL);
        if (TextUtils.isEmpty(channel)) {
            channel = Tools.getMetaValue(InstagmeApp.getContext(), "SENSORS_ANALYTICS_UTM_SOURCE");
        }

        return TextUtils.isEmpty(channel)?"10000":channel;
    }

    /**
     * <br> ClassName:   Tools
     * <br> Description: 获取设备类型
     * <br>
     * <br> Author:      zhangweiqiang
     * <br> Date:        2017/8/8 20:39
     */
    public static String getDeviceType() {
        return "8";
    }

    // 获取ApiKey
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return apiKey;
    }

    /**
     * <br> ClassName:   Tools
     * <br> Description: 获取状态栏高度
     * <br>
     * <br> Author:      zhangweiqiang
     * <br> Date:        2017/8/8 21:39
     */
    public static int getStatusHeight() {
        int result = 0;
        int resourceId = InstagmeApp.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = InstagmeApp.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * <br> Description: 隐藏软键盘
     * <br> Author:      zhangweiqiang
     * <br> Date:        2017/9/4 10:48
     */
    public static void hideSoftInput(View view) {
        if (view == null || view.getContext() == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
