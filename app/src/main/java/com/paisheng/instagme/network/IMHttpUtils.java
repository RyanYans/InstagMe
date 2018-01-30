package com.paisheng.instagme.network;

import android.support.annotation.Nullable;

import com.paisheng.instagme.constant.AppApiConstant;
import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.network.Smart;

import java.util.HashMap;

/**
 * @author: liaoshengjian
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2017/8/3 10:54
 */
    public class IMHttpUtils {

    public static RequestCall postIM(String method, @Nullable HashMap<String,String> params) {
        String url = AppApiConstant.GLOBAL_IP + method;

        return Smart.post(url).addParams(params);
    }
}
