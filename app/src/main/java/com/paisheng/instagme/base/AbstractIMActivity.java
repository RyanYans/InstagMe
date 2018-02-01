package com.paisheng.instagme.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.paisheng.instagme.network.bean.ResultErrorInfo;
import com.paisheng.instagme.network.bean.ServerDownInfoBean;
import com.paisheng.instagme.utils.Tools;
import com.paisheng.lib.mvp.base.AbstractMvpActivity;
import com.paisheng.lib.mvp.base.BasePresenter;
import com.paisheng.lib.mvp.base.IMvpView;
import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.network.exception.ApiException;
import com.paisheng.lib.util.ToastUtil;
import com.paisheng.lib.widget.dialog.ProgressHUD;
import com.paisheng.lib.widget.reloadview.PageTips;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author: yuanbaining
 * @Filename: AbstractIMActivity
 * @Description:    Activity中间层
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/29 9:37
 */


public abstract class AbstractIMActivity<T extends BasePresenter> extends AbstractMvpActivity<T>
        implements IBaseView {

    private T mPresenter;
    /*** 主RootView ***/
    private LinearLayout mLayoutMain;
    /*** 主ContentView ***/
    private View mContentView;
    private Unbinder mBinder;
    private RelativeLayout mRlTitle;
    private ProgressHUD mProgressHUD;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLayoutMain = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        mLayoutMain.setLayoutParams(layoutParams);
        mLayoutMain.setOrientation(LinearLayout.VERTICAL);

        setContentView(mLayoutMain);
    }

    public LinearLayout getMainLayout() {
        return mLayoutMain;
    }

    /**
     * <br> Description: 添加布局到主界面
     * <br> Author:      yuanbaining
     * <br> Date:        2018/1/29 21:00
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mContentView = getLayoutInflater().inflate(layoutResID, null);
        mLayoutMain.addView(mContentView);

        bindView(true);
    }

    /**
     * <br> Description: 添加布局到主界面并添加头布局
     * <br> Author:      yuanbaining
     * <br> Date:        2018/1/29 21:00
     */
    public void setContentView(@LayoutRes int layoutResID, @LayoutRes int headerResID) {
        // 添加头布局至MainLayout
        mRlTitle = (RelativeLayout) getLayoutInflater().inflate(layoutResID, mLayoutMain, false);
        getMainLayout().addView(mRlTitle);

        mContentView = getLayoutInflater().inflate(layoutResID, null);
        mLayoutMain.addView(mContentView);

        bindView(true);
    }

    /**
     * <br> Description: 绑定/解绑定 ButterKnife
     * <br> Author:      yuanbaining
     * <br> Date:        2018/1/29 9:56
     */
    private void bindView(boolean bind) {
        if (!bind && (mBinder != null)) {
            mBinder.unbind();
            mBinder = null;
            return;
        }
        if (bind && (mContentView != null)) {
            mBinder = ButterKnife.bind(this);
        }
    }

    @Override
    public void showLoading(String tips) {
        dismissLoading();

        if (!TextUtils.isEmpty(tips) && !isFinishing()) {
            mProgressHUD = ProgressHUD.show(AbstractIMActivity.this, tips);
        }
    }

    @Override
    public void dismissLoading() {
        if (mProgressHUD != null && !isFinishing()) {
            mProgressHUD.dismiss();
            mProgressHUD = null;
        }
    }

    @Override
    public void showToast(String info) {
        if (!TextUtils.isEmpty(info)) {
            Tools.hideSoftInput(getMainLayout());
            ToastUtil.showToast(info, getMainLayout());
        }
    }

    /**
     *<br> Description: Activity销毁时解绑binder
     *<br> Author:      yuanbaining
     *<br> Date:        2018/2/1 18:28
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mProgressHUD != null) {
            mProgressHUD.dismiss();
            mProgressHUD = null;
        }
        bindView(false);
    }

    @Override
    public void displayRquestServerDown(String taskId, ServerDownInfoBean serverDownInfo) {

    }

    @Override
    public void displaySuccess(String taskId, Object result) {

    }

    @Override
    public void displayRequestFailure(String taskId, ApiException e) {

    }

    @Override
    public void displayNetworkError(String taskId, ApiException e) {

    }

    @Override
    public void displayRequestNotNet(String taskId, ApiException e) {

    }
}
