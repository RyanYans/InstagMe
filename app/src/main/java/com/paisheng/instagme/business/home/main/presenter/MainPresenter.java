package com.paisheng.instagme.business.home.main.presenter;


import com.paisheng.instagme.base.BaseIMPresenter;
import com.paisheng.instagme.business.home.main.contract.IMainActivityContract;
import com.paisheng.lib.mvp.base.BasePresenter;

/**
 * @author: yuanbaining
 * @Filename: MainPresenter
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/26 9:11
 */


public class MainPresenter extends BaseIMPresenter<IMainActivityContract.IView>
        implements IMainActivityContract.IPresenter {
    private long intervalTimer = 0;

    @Override
    public void exit() {
        if ((System.currentTimeMillis() - intervalTimer) > 2000) {
            intervalTimer = System.currentTimeMillis();
            if (isViewAttached()) {
                getView().showToast("再按一次退出应用");
            }
        } else {
            if (isViewAttached()) {
                getView().onFinish();
            }
        }
    }
}
