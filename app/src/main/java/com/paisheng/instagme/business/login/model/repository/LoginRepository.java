package com.paisheng.instagme.business.login.model.repository;

import com.paisheng.instagme.base.IBaseView;
import com.paisheng.instagme.business.login.constant.LoginMethodConstant;
import com.paisheng.instagme.business.login.model.entity.LoginResultInfo;
import com.paisheng.instagme.common.netcallback.ICommonRequestCallback;
import com.paisheng.instagme.network.IMHttpUtils;
import com.paisheng.instagme.network.callback.IMCallback;
import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.network.exception.ApiException;

import java.util.HashMap;

/**
 * @author: yuanbaining
 * @Filename: LoginRepository
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/29 15:19
 */


public class LoginRepository {
    public static void toLoginNet(String email, String password, IBaseView baseView, final ICommonRequestCallback<LoginResultInfo> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "login");
        params.put("rememberme", "on");
        params.put("email", email);
        params.put("password", password);

        RequestCall call = IMHttpUtils.postIM(LoginMethodConstant.LOGIN_LOGIN_METHOD, params)
                .execute(new IMCallback<LoginResultInfo>(baseView, "登陆中..") {
                    @Override
                    public void onSuccess(LoginResultInfo response) {
                            callback.onSuccess((LoginResultInfo) response);
                    }

                    @Override
                    public void onFailure(RequestCall requestCall, ApiException e) {
                        super.onFailure(requestCall, e);

                        callback.onFailure(requestCall, e);
                    }
                });


        System.out.println("=========================>" + call.getUrl());
    }
}
