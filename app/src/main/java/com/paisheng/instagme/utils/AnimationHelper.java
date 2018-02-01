package com.paisheng.instagme.utils;

import android.app.Activity;
import android.os.Build;

import com.paisheng.instagme.R;

/**
 * @description:
 * @author: Match
 * @date: 1/30/17
 */

public class AnimationHelper {

    public static void setRotateTransition(Activity activity) {
        if (activity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            activity.overridePendingTransition(R.anim.pic_in, 0);
        }
    }

    public static void setFadeTransition(Activity activity) {
        if (activity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}
