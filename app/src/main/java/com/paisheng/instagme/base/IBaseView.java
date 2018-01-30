package com.paisheng.instagme.base;


import com.paisheng.instagme.network.bean.ResultErrorInfo;
import com.paisheng.instagme.network.bean.ServerDownInfoBean;
import com.paisheng.lib.mvp.base.IMvpView;

/**
 * @author: yuanbaining
 * @Filename: IBaseView
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/24 17:12
 */

public interface IBaseView extends IMvpView {
    void displayRequestFailure(String taskId, ResultErrorInfo result);

    void displayNetworkError(String taskId, ResultErrorInfo result);

    void displayRequestNotNet(String taskId);

    void displaySuccess(String taskId, Object result);

    void displayRquestServerDown(String taskId, ServerDownInfoBean serverDownInfo);
}
