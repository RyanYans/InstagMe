package com.paisheng.instagme.business.home.mytab.model.repository;

import com.paisheng.instagme.base.IBaseView;
import com.paisheng.instagme.business.home.mytab.model.entity.MyResultInfo;
import com.paisheng.instagme.common.netcallback.ICommonRequestCallback;
import com.paisheng.instagme.network.IMHttpUtils;
import com.paisheng.instagme.network.callback.IMCallback;
import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.network.exception.ApiException;

import java.util.HashMap;

/**
 * @author: yuanbaining
 * @Filename: MyFragmentRepository
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/31 16:23
 */

public class MyFragmentRepository {

    /**
     *<br> Description: 网络加载图片信息
     *<br> Author:      yuanbaining
     *<br> Date:        2018/2/1 19:32
     * @param baseView  父布局
     * @param callback  回掉接口
     */
    public static void loadUserData(IBaseView baseView, String method,
                                    final ICommonRequestCallback<MyResultInfo> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "userconfig");

        IMHttpUtils.GetIM(method, params)
                .execute(new IMCallback<MyResultInfo>(baseView) {
                    @Override
                    public void onSuccess(MyResultInfo response) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onFailure(RequestCall requestCall, ApiException e) {
                        super.onFailure(requestCall, e);
                        callback.onFailure(requestCall, e);
                    }
                });
    }
}
