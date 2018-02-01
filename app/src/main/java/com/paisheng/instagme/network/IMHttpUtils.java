package com.paisheng.instagme.network;

import android.support.annotation.Nullable;

import com.paisheng.instagme.constant.AppApiConstant;
import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.network.Smart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

    public static RequestCall GetIM(String method, @Nullable HashMap<String,String> params) {
        String baseUrl = AppApiConstant.GLOBAL_IP + method;

        String url = jointUrl(baseUrl, params);

        return Smart.get(url);
    }

    public static RequestCall GetIM4Pics(String method, HashMap<String, String> params) {
        String baseUrl = AppApiConstant.GLOBAL_PIC_IP + method;

        String url = jointUrl(baseUrl, params);

        return Smart.get(url);
    }

    private static String jointUrl(String baseUrl, HashMap<String, String> params) {
        if (params == null || params.size() <= 0) {
            return baseUrl;
        }
        StringBuilder builder = new StringBuilder(baseUrl);
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();

        if (baseUrl.contains("?")) {
            // 已含字段
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                builder.append("&").append(next.getKey()).append("=").append(next.getValue());
            }
        } else {
            // 不含字段
            // 首字段
            Map.Entry<String, String> firstNext = iterator.next();
            builder.append("?").append(firstNext.getKey()).append("=").append(firstNext.getValue());

            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                builder.append("&").append(next.getKey()).append("=").append(next.getValue());
            }
        }

        return builder.toString();
    }



}
