package com.paisheng.instagme.network.exception;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.provider.Settings;
import android.widget.TextView;

import com.paisheng.instagme.R;
import com.paisheng.instagme.utils.DialogUtil;
import com.paisheng.instagme.widget.dialog.SecondTipDialog;
import com.paisheng.lib.util.AppUtil;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import javax.net.ssl.SSLException;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/5/11 15:15
 */
public class HttpRequestErrorUtil {
    // 网络层错误
    private static final String str_default = "访问失败：100";//未知错误
    private static final String str_SocketTimeoutException = "网络访问异常，请检查您的网络：101";// 超时
    private static final String str_SocketException = "网络访问异常，请检查您的网络：102";// 不能连接网络（包含无网络，以及被禁用网络）
    private static final String str_SLException = "网络访问异常，请检查您的网络：103";// SSL验证失败
    private static final String str_disable_network = "无法访问，应用的网络访问权限被禁用：104";// 被禁用网络
    public static final String str_not_network = "当前网络不可用，请检查网络设置";//用户无开启网络

    //非网络层错误
    public static final String str_userinfo_error = "数据异常: 500";// 请求JSON数据异常
    public static final String str_dataResolveError_1 = "数据解析异常：501";//JSON解析错误
    public static final String str_dataResolveError_2 = "数据解析异常：502";//解密错误
    public static final String str_dataResolveError_3 = "数据解析异常：503";//加密错误
    public static final String str_dataResolveError_4 = "数据解析异常：504";//JSON解析错误
    public static final String str_dataResolveError_5 = "数据异常：505";//找不到相关类
    public static final String str_dataResolveError_6 = "数据异常：506";//其它错误
    public static final String str_data_empt = "数据异常：507";//服务器数据错误
    public static final String str_data_no_ReturnCode = "数据异常：508";//服务器数据错误

    public static String getErrorInfo(Exception e) {
        String message = str_default + e.toString();
        if (e instanceof SocketTimeoutException) {// 超时
            message = str_SocketTimeoutException;
        } else if (e instanceof SocketException) {// 不能连接网络（包含无网络，以及被禁用网络）
            message = str_SocketException;
            AppUtil.isNetWorkAvailable();
            if (e.toString().contains("127.0.0.1")) {
                message = str_disable_network;
            }
        } else if (e instanceof SSLException) {// SSL验证失败
            message = str_SLException;
        }
        return message;
    }

    private static Dialog dialog;

    public static void showErrorNetworkDialog(final Activity activity, final String message) {
        synchronized (HttpRequestErrorUtil.class) {
            String shortMessage = message.split("：")[0];
            if (dialog != null && dialog.isShowing()) {
                ((TextView) dialog.findViewById(R.id.txtMessage)).setText(shortMessage);
            } else {
                String strConfirm = "取消";
                if (message.equals(str_not_network)) {
                    strConfirm = "设置";
                }
                if (message.equals(str_disable_network)) {
                    strConfirm = "";
                }
                dialog = DialogUtil.showTipsDialog(activity, "", shortMessage, strConfirm, "确定", new SecondTipDialog.OnConfirmListener() {
                    @Override public void clickConfirm() {
                        if (message.equals(str_not_network)) {
                            activity.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        } else {//TODO
                            //activity.startActivity(new Intent(activity, NetworkDiagnosisActivity.class).putExtra("error", message));
                        }
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                            dialog = null;
                        }
                    }

                    @Override public void clickCancel() {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                            dialog = null;
                        }
                    }
                });
            }
        }
    }

    public static void dismissDialog() {
        synchronized (HttpRequestErrorUtil.class) {
            //捕捉在非法生命周期内使用dialog.dismiss()引起的IllegalArgumentException错误
            try {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    dialog = null;
                }
            } catch (IllegalArgumentException e) {
                dialog = null;
            } finally {
                dialog = null;
            }
        }
    }



}
