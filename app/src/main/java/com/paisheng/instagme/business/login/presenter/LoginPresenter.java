package com.paisheng.instagme.business.login.presenter;

import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.paisheng.instagme.business.login.contract.ILoginContract;
import com.paisheng.instagme.business.login.model.entity.LoginResultInfo;
import com.paisheng.instagme.business.login.model.repository.LoginRepository;
import com.paisheng.instagme.common.arouter.MainRouterConstant;
import com.paisheng.instagme.common.netcallback.CommonRequestCallback;
import com.paisheng.instagme.utils.SharedPreference;
import com.paisheng.lib.mvp.base.BasePresenter;
import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.network.exception.ApiException;
import com.paisheng.lib.util.ValidateUtil;

/**
 * @author: yuanbaining
 * @Filename: LoginPresenter
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/29 9:32
 */

public class LoginPresenter extends BasePresenter<ILoginContract.IView> implements ILoginContract.IPresenter {

    @Override
    public void toLoginNet(String email, String password) {
        boolean validity = checkLoginWord(email, password);
        //getView().showLoading("登陆中..");

        if (validity) {
            LoginRepository.toLoginNet(email, password, getView(), new CommonRequestCallback<LoginResultInfo>(){
                @Override
                public void onSuccess(LoginResultInfo loginResultInfo) {
                    super.onSuccess(loginResultInfo);
                    if (loginResultInfo.isSuccess()) {
                        System.out.println("登陆成功~");
                        ARouter.getInstance().build(MainRouterConstant.MAIN_PAGE).navigation();
                    } else {
                        getView().showToast(loginResultInfo.getError());
                    }
                }

                @Override
                public void onFailure(RequestCall requestCall, ApiException e) {
                    super.onFailure(requestCall, e);
                    getView().showToast("登陆失败,请检查当前网络状况~");
                }
            });
        }
    }

    @Override
    public void toLoginSkip() {
        SharedPreference.getInstance().putValue("login", false);
    }

    private boolean checkLoginWord(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            getView().showToast("请输入邮箱名");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            getView().showToast("请输入密码");
            return false;
        }
        if (!ValidateUtil.isValidateEmial(email)) {
            getView().showToast("邮箱格式不正确");
            return false;
        }
        if (!ValidateUtil.isValidatePassword(password)) {
            getView().showToast("密码格式错误，长度6-16位");
            return false;
        }
        return true;
    }
}
