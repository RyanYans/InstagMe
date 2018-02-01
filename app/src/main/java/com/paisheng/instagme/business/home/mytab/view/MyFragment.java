package com.paisheng.instagme.business.home.mytab.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.paisheng.instagme.R;
import com.paisheng.instagme.base.AbstractIMFragment;
import com.paisheng.instagme.business.home.mytab.contract.IMyFragmentContract;
import com.paisheng.instagme.business.home.mytab.model.entity.MyResultInfo;
import com.paisheng.instagme.business.home.mytab.presenter.MyFragmentPresenter;
import com.paisheng.instagme.common.arouter.LoginRouterConstant;
import com.paisheng.instagme.constant.UserConfig;
import com.paisheng.instagme.utils.DialogUtil;
import com.paisheng.instagme.utils.SharedPreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * @author: yuanbaining
 * @Filename: MyFragment
 * @Description:    个人中心界面
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/29 9:33
 */

public class MyFragment extends AbstractIMFragment<MyFragmentPresenter> implements IMyFragmentContract.IView {
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_id)
    TextView mTvUserId;
    @BindView(R.id.tv_user_gender)
    TextView mTvUserGender;
    @BindView(R.id.tv_user_regtimer)
    TextView mTvUserRegtimer;
    @BindView(R.id.btn_unlogin)
    Button mBtnUnlogin;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    private MyFragmentPresenter mMyFragmentPresenter;

    @Override
    protected MyFragmentPresenter createPresenter() {
        mMyFragmentPresenter = new MyFragmentPresenter();
        return mMyFragmentPresenter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my;
    }

    @Override
    protected void lazyLoad() {
        initView();

        initData();
    }

    private void initView() {
        boolean isLogin = SharedPreference.getInstance().getBooleanValue(UserConfig.USER_LOGINED);
        if (isLogin) {
            mBtnLogin.setVisibility(View.GONE);
            mBtnUnlogin.setVisibility(View.VISIBLE);
        } else {
            mBtnUnlogin.setVisibility(View.GONE);
            mBtnLogin.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        mMyFragmentPresenter.loadUserData();
    }

    @Override
    public void showUserData(MyResultInfo info) {
        if (info != null) {
            mTvUserName.setText(info.getUsername());
            mTvUserId.setText(info.getUid());
            mTvUserGender.setText(info.getGender() == 1 ? "男" : "女");
            mTvUserRegtimer.setText(info.getRegdate());
        }
    }

    @Override
    public void showLoginDialog(String title, String content) {
        AlertDialog dialog = DialogUtil.showDialog(getActivity(), title, content, "确认", "取消", new DialogUtil.OnConfirmListener() {
            @Override
            public void clickConfirm() {
                if (mMyFragmentPresenter != null) {
                    mMyFragmentPresenter.userLogin();
                }
            }
            @Override
            public void clickCancel() {
            }
        });
    }

    @OnClick({R.id.btn_login, R.id.btn_unlogin})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mMyFragmentPresenter.userLogin();
                break;
            case R.id.btn_unlogin:
                mMyFragmentPresenter.userUnLogin();
                break;
        }
    }
}
