package com.paisheng.instagme.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.paisheng.instagme.utils.Tools;
import com.paisheng.lib.mvp.base.BasePresenter;
import com.paisheng.lib.mvp.base.IMvpView;
import com.paisheng.lib.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author: yuanbaining
 * @Filename: AbstractMvpActivity
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/25 16:00
 */


public abstract class AbstractMvpActivity<T extends BasePresenter> extends AppCompatActivity implements IMvpView {

    private T mPresenter;
    private LinearLayout mLayoutMain;
    private View mContentView;
    private Unbinder mBinder;
    private RelativeLayout mRlTitle;

    protected abstract T createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLayoutMain = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        mLayoutMain.setLayoutParams(layoutParams);
        mLayoutMain.setOrientation(LinearLayout.VERTICAL);

        setContentView(mLayoutMain);

        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);

        }
    }

    public LinearLayout getMainLayout() {
        return mLayoutMain;

    }


    /**
     *<br> Description: 添加布局到主界面
     *<br> Author:      yuanbaining
     *<br> Date:        2018/1/25 21:00
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mContentView = getLayoutInflater().inflate(layoutResID, null);
        mLayoutMain.addView(mContentView, new ViewGroup.LayoutParams(-1, -1));

        bindView(true);
    }

    /**
     *<br> Description: 添加布局到主界面并添加头布局
     *<br> Author:      yuanbaining
     *<br> Date:        2018/1/25 21:00
     */
    public void setContentView(@LayoutRes int layoutResID, @LayoutRes int headerResID) {
        // 添加头布局至MainLayout
        mRlTitle = (RelativeLayout) getLayoutInflater().inflate(layoutResID, mLayoutMain, false);
        getMainLayout().addView(mRlTitle);

        mContentView = getLayoutInflater().inflate(layoutResID, null);
        mLayoutMain.addView(mContentView, new ViewGroup.LayoutParams(-1, -1));

        bindView(true);
    }

    private void bindView(boolean bind) {
        if (!bind && (mBinder != null)) {
            mBinder.unbind();
            mBinder = null;
            return;
        }
        if (bind && (mContentView != null)) {
            mBinder = ButterKnife.bind(mContentView);
        }
    }

    @Override
    public void showLoading(String tips) {
        dismissLoading();
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showToast(String info) {
        if (!TextUtils.isEmpty(info)) {
            Tools.hideSoftInput(getMainLayout());
            ToastUtil.showToast(info, getMainLayout());
        }
    }

    @Override
    public void onFinish() {
        if (!isFinishing()) {
            this.finish();
        }
    }
}