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
import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.util.ToastUtil;
import com.paisheng.lib.widget.dialog.ProgressHUD;
import com.paisheng.lib.widget.reloadview.PageTips;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author: yuanbaining
 * @Filename: AbstractIMActivity
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/29 9:37
 */


public abstract class AbstractIMActivity<T extends BasePresenter> extends AbstractMvpActivity<T> implements IBaseView {

    private T mPresenter;
    private LinearLayout mLayoutMain;
    private View mContentView;
    private Unbinder mBinder;
    private RelativeLayout mRlTitle;
    private ProgressHUD mProgressHUD;

    /***是否打开重新加载***/
    private boolean mIsOpenReload;
    /***提示内容***/
    private PageTips mPageTips;
    /***当前是否显示了情感图***/
    protected boolean mIsShowReload = false;
    /***设置不提示错误信息***/
    private RequestCall mCloseShowErrorInfoTaskId;

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
     * <br> Date:        2018/1/25 21:00
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
     * <br> Date:        2018/1/25 21:00
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

    @Override
    public void displaySuccess(String taskId, Object result) {

    }

    @Override
    public void displayRequestNotNet(String taskId) {

    }

    @Override
    public void displayNetworkError(String taskId, ResultErrorInfo result) {

    }

    @Override
    public void displayRquestServerDown(String taskId, ServerDownInfoBean serverDownInfo) {

    }

    @Override
    public void displayRequestFailure(String taskId, ResultErrorInfo result) {

    }

    /**
     * <br> Description: 显示、隐藏情感图
     * <br> Author:      liaoshengjian
     * <br> Date:        2017/5/24 17:20
     *
     * @param visibility    是否可见
     * @param currentTaskId 指定重新加载的任务
     * @param taskId        当前请求任务
     * @param resultType    类型
     */
    public void setVisibilityReloadView(int visibility, String currentTaskId, String taskId, int resultType) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mProgressHUD != null) {
            mProgressHUD.dismiss();
            mProgressHUD = null;
        }

        bindView(false);
    }
}
