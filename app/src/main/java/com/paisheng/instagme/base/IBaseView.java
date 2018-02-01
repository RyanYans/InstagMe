package com.paisheng.instagme.base;


import com.paisheng.instagme.network.bean.ResultErrorInfo;
import com.paisheng.instagme.network.bean.ServerDownInfoBean;
import com.paisheng.lib.mvp.base.IMvpView;
import com.paisheng.lib.mvp.network.INetworkView;

/**
 * @author: yuanbaining
 * @Filename: IBaseView
 * @Description:    V层核心接口
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/24 17:12
 */

public interface IBaseView extends INetworkView {
    void displayRquestServerDown(String taskId, ServerDownInfoBean serverDownInfo);
}
