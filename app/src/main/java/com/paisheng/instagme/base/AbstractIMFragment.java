package com.paisheng.instagme.base;

import android.view.View;

import com.paisheng.instagme.network.bean.ServerDownInfoBean;
import com.paisheng.lib.mvp.extend.AbstractLazyFragment;
import com.paisheng.lib.mvp.network.NetworkPresenter;
import com.paisheng.lib.widget.dialog.ProgressHUD;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author: yuanbaining
 * @Filename: AbstractIMFragment
 * @Description: Fragment中间层，预留
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/30 13:59
 */


public abstract class AbstractIMFragment<T extends NetworkPresenter> extends AbstractLazyFragment<T> implements IBaseView {
    protected ProgressHUD mProgressHUD;
    private Unbinder mUnbinder;

    /**
     *<br> Description: 延时后先加载布局
     *<br> Author:      yuanbaining
     *<br> Date:        2018/2/1 18:25
     */
    protected abstract int getLayoutRes();

    /**
     *<br> Description: 延时后再加载执行
     *<br> Author:      yuanbaining
     *<br> Date:        2018/2/1 18:24
     */
    protected abstract void lazyLoad();

    protected View getMainLayout() {
        return mRootView;
    }

    @Override
    protected View onLoadUI() {
        mVsContent.setLayoutResource(getLayoutRes());
        View contentView = mVsContent.inflate();
        bindView(true, contentView);

        lazyLoad();

        return contentView;
    }

    @Override
    protected void setPageId() {

    }

    /**
     *<br> Description: Fragment销毁时解绑binder
     *<br> Author:      yuanbaining
     *<br> Date:        2018/2/1 18:26
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 解绑
        bindView(false, null);
    }

    /**
     * <br> Description: 绑定/解绑定 ButterKnife
     * <br> Author:      yuanbaining
     * <br> Date:        2018/1/30 15:16
     */
    protected void bindView(boolean bind, View view) {
        if (!bind && mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
            return;
        }
        if (bind && view != null) {
            mUnbinder = ButterKnife.bind(this, view);
        }
    }

    @Override
    public void displayRquestServerDown(String taskId, ServerDownInfoBean serverDownInfo) {

    }
}
