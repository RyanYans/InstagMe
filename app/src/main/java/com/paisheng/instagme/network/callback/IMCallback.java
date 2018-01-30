package com.paisheng.instagme.network.callback;

import android.text.TextUtils;

import com.paisheng.instagme.base.IBaseView;
import com.paisheng.instagme.network.bean.ResultErrorInfo;
import com.paisheng.instagme.network.bean.ServerDownInfoBean;
import com.paisheng.instagme.network.exception.HttpRequestErrorUtil;
import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.network.exception.ApiException;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author: liaoshengjian
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2017/8/4 18:22
 */
public abstract class IMCallback<T> extends NetworkCallback<T> {

    private Reference<IBaseView> mIBaseView;
    private String mTips;

    /**
     *<br> Description: 构造函数
     *<br> Author:      liaoshengjian
     *<br> Date:        2017/6/13 16:01
     *
     * @param iBaseView IBaseView
     */
    public IMCallback(IBaseView iBaseView) {
        if (iBaseView != null) {
            this.mIBaseView = new WeakReference(iBaseView);
        }
    }

    /**
     *<br> Description: 构造函数
     *<br> Author:      liaoshengjian
     *<br> Date:        2017/6/13 16:01
     *
     * @param iBaseView IBaseView
     * @param mTips String
     */
    public IMCallback(IBaseView iBaseView, String mTips) {
        if (iBaseView != null) {
            this.mIBaseView = new WeakReference(iBaseView);
        }
        this.mTips = mTips;
    }

    /**
     *<br> Description: 获取View
     *<br> Author:      liaoshengjian
     *<br> Date:        2017/6/13 16:01
     *
     * @return IBaseView
     */
    private IBaseView getIBaseView() {
        if (mIBaseView != null && mIBaseView.get() != null) {
            return mIBaseView.get();
        }
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getIBaseView() != null) {
            getIBaseView().showLoading(mTips);
        }
    }

    @Override
    public void onFailure(RequestCall requestCall, ApiException e) {
        if (getIBaseView() == null) {
            return;
        }

        switch (e.getCode()) {
            //无网络
            case ApiException.CODE_NO_NETWORK:
                getIBaseView().displayRequestNotNet(requestCall.getTaskId());
                break;
            //请求失败onFailure
            case ApiException.CODE_OKHTTP_FAILURE:
                if (!e.toString().contains("Canceled")) {
                    //取消请求不做提示
                    getIBaseView().displayNetworkError(requestCall.getTaskId(),
                            new ResultErrorInfo(HttpRequestErrorUtil.getErrorInfo(e), e.getUrl()));
                }
                break;
            case ApiException.CODE_OKHTTP_NO_SUCCESS:
                if (TextUtils.isEmpty(e.getMessage()) || e.getMessage().contains("DOCTYPE")) {
                    e.setMessage("请求失败");
                }
                getIBaseView().displayRequestFailure(requestCall.getTaskId(), new ResultErrorInfo(e.getMessage(), "0"));
                break;
            //JSON解析错误
            case ApiException.CODE_RES_JSON_PARSE_ERROR:
                getIBaseView().displayRequestFailure(requestCall.getTaskId(), new ResultErrorInfo(e.getMessage(), "0"));
                break;
            //解密错误
            case ApiException.CODE_RES_DECODE_ERROR:
                getIBaseView().displayRequestFailure(requestCall.getTaskId(), new ResultErrorInfo(e.getMessage(), "0"));
                break;
            //空数据
            case ApiException.CODE_RES_DATA_EMPTY:
                getIBaseView().displayRequestFailure(requestCall.getTaskId(), new ResultErrorInfo(e.getMessage(), "0"));
                break;
            //没有ReturnCode
            case ApiException.CODE_RES_NO_RETURN_CODE:
                getIBaseView().displayRequestFailure(requestCall.getTaskId(), new ResultErrorInfo(e.getMessage(), "0"));
                break;
            //其他请求错误
            case ApiException.CODE_REQ_OTHER_ERROR:
                getIBaseView().displayRequestFailure(requestCall.getTaskId(), (ResultErrorInfo) e.getResultObject());
                break;
            //解析出错
            case ApiException.CODE_RES_JSON_PARSE_ERROR_2:
                getIBaseView().displayRequestFailure(requestCall.getTaskId(), new ResultErrorInfo(e.getMessage(), "0"));
                break;
            //其他错误
            case ApiException.CODE_RES_OTHER_ERROR:
                getIBaseView().displayRequestFailure(requestCall.getTaskId(), new ResultErrorInfo(e.getMessage(), "0"));
                break;
            //停机维护
            case ApiException.CODE_REQ_SERVER_DOWN:
                ServerDownInfoBean serverDownInfo = (ServerDownInfoBean) e.getResultObject();
                getIBaseView().displayRquestServerDown(requestCall.getTaskId(), serverDownInfo);
                break;
        }



    }

}
