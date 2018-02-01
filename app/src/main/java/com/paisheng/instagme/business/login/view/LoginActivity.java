package com.paisheng.instagme.business.login.view;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.view.RxView;
import com.paisheng.instagme.R;
import com.paisheng.instagme.base.AbstractIMActivity;
import com.paisheng.instagme.business.login.contract.ILoginContract;
import com.paisheng.instagme.business.login.presenter.LoginPresenter;
import com.paisheng.instagme.common.arouter.LoginRouterConstant;
import com.paisheng.instagme.common.grant.CommonPermissionCallBack;
import com.paisheng.lib.grant.core.PermissionRequestFactory;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * @author: yuanbaining
 * @Filename: LoginActivity
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/26 18:00
 */

@Route(path = LoginRouterConstant.LOGIN_PAGE, name = "登陆页面")
public class LoginActivity extends AbstractIMActivity<LoginPresenter>
        implements ILoginContract.IView {

    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_login_skip)
    TextView mTvLoginSkip;
    @BindView(R.id.et_login_email)
    EditText mEtLoginEmail;
    @BindView(R.id.et_login_password)
    EditText mEtLoginPassword;
    @BindView(R.id.iv_login_see)
    ImageView mIvLoginSee;
    private LoginPresenter mLoginPresenter;
    /*** Password显示/隐藏标志位 ***/
    private boolean isPasswordShow = false;

    @Override
    protected LoginPresenter createPresenter() {
        mLoginPresenter = new LoginPresenter();
        return mLoginPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        // 启动时，申请位置权限
        PermissionRequestFactory.create(this)
                .addPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .request(new CommonPermissionCallBack(this));
    }

    private void initView() {
        RxView.clicks(mBtnLogin)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        String email = mEtLoginEmail.getText().toString();
                        String password = mEtLoginPassword.getText().toString();
                        mLoginPresenter.toLoginNet(email, password);
                    }
                });
    }

    @OnClick({R.id.iv_login_see, R.id.tv_login_skip})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_see:
                changePsdShow();
                break;
            case R.id.tv_login_skip:
                mLoginPresenter.toLoginSkip();
                break;
            default:
                break;
        }
    }

    /**
     *<br> Description: 修改密码显示隐藏
     *<br> Author:      yuanbaining
     *<br> Date:        2018/2/1 18:43
     */
    private void changePsdShow() {
        if (isPasswordShow) {
            // 显示 -> 隐藏
            isPasswordShow = false;
            mIvLoginSee.setImageResource(R.drawable.ic_login_unseen_password);
            mEtLoginPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mEtLoginPassword.setSelection(mEtLoginPassword.getText().length());
        } else {
            // 隐藏 -> 显示
            isPasswordShow = true;
            mIvLoginSee.setImageResource(R.drawable.ic_login_see_password);
            mEtLoginPassword.setInputType(InputType.TYPE_CLASS_TEXT);
            mEtLoginPassword.setSelection(mEtLoginPassword.getText().length());
        }
    }
}
