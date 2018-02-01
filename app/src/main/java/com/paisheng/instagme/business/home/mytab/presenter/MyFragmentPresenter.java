package com.paisheng.instagme.business.home.mytab.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.paisheng.instagme.InstagmeApp;
import com.paisheng.instagme.base.BaseIMPresenter;
import com.paisheng.instagme.business.home.mytab.constant.MyMethodConstant;
import com.paisheng.instagme.business.home.mytab.contract.IMyFragmentContract;
import com.paisheng.instagme.business.home.mytab.model.entity.MyResultInfo;
import com.paisheng.instagme.business.home.mytab.model.repository.MyFragmentRepository;
import com.paisheng.instagme.business.home.mytab.view.MyFragment;
import com.paisheng.instagme.common.arouter.LoginRouterConstant;
import com.paisheng.instagme.common.netcallback.ICommonRequestCallback;
import com.paisheng.instagme.constant.UserConfig;
import com.paisheng.instagme.utils.DialogUtil;
import com.paisheng.instagme.utils.SharedPreference;
import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.network.exception.ApiException;


/**
 * @author: yuanbaining
 * @Filename: MyFragmentPresenter
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/30 19:27
 */

public class MyFragmentPresenter extends BaseIMPresenter<MyFragment>
        implements IMyFragmentContract.IPresenter{

    @Override
    public void loadUserData() {
        if (!checkUserLogin()) {
            getView().showLoginDialog("尚未登陆", "立即登录个人账户？");
            return;
        }

        getView().showLoading("加载中..");
        MyFragmentRepository.loadUserData(getView(), MyMethodConstant.USER_DATA_METHOD, new ICommonRequestCallback<MyResultInfo>() {
            @Override
            public void onSuccess(MyResultInfo myResultInfo) {
                getView().showUserData(myResultInfo);
                getView().dismissLoading();
            }

            @Override
            public void onFailure(RequestCall requestCall, ApiException e) {
                getView().showToast("用户数据加载失败");
                getView().dismissLoading();
            }
        });
    }

    /**
     *<br> Description: 查看用户是否已登陆
     *<br> Author:      yuanbaining
     *<br> Date:        2018/2/1 19:34
     */
    private boolean checkUserLogin() {
        return SharedPreference.getInstance().getBooleanValue(UserConfig.USER_LOGINED);
    }

    @Override
    public void userUnLogin() {
        SharedPreference.getInstance().putValue(UserConfig.USER_LOGINED, false);    // 删除登陆缓存
        ARouter.getInstance().build(LoginRouterConstant.LOGIN_PAGE).navigation();
        getView().onFinish();
    }

    @Override
    public void userLogin() {
        ARouter.getInstance().build(LoginRouterConstant.LOGIN_PAGE).navigation();
        getView().onFinish();
    }
}
