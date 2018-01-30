package com.paisheng.instagme.common.pulltorefresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paisheng.instagme.R;
import com.paisheng.lib.util.UiUtil;
import com.paisheng.lib.vendor.ptr.PtrFrameLayout;
import com.paisheng.lib.vendor.ptr.PtrUIHandler;
import com.paisheng.lib.vendor.ptr.indicator.PtrIndicator;

/**
 * @author: liaoshengjian
 * @Filename:
 * @Description: 自定义默认LoadingFooterView
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2017/1/16 16:35
 */
public class CommonPtrDefaultFooter extends LinearLayout implements PtrUIHandler {

    //    private ImageView ivTDPtrLoadMoreImg;
    private TextView tvTdPtrLoadMoreTxt;

    /**
     * 动画
     */
    private AnimationDrawable mAnimation;

    public CommonPtrDefaultFooter(Context context) {
        super(context, null);
        initFootView();
    }

    public CommonPtrDefaultFooter(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initFootView();
    }

    public CommonPtrDefaultFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFootView();
    }

    private void initFootView() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        setPadding(UiUtil.dp2px(24), UiUtil.dp2px(12)
                , UiUtil.dp2px(24), UiUtil.dp2px(12));
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ptr_common_load_more, this, true);
//        this.ivTDPtrLoadMoreImg = (ImageView) view.findViewById(R.id.ivTDPtrLoadMoreImg);
        this.tvTdPtrLoadMoreTxt = (TextView) view.findViewById(R.id.tvTdPtrLoadMoreTxt);
    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        tvTdPtrLoadMoreTxt.setText("加载中...");
        Drawable drawable = getResources().getDrawable(R.drawable.progress_loading_anim);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvTdPtrLoadMoreTxt.setCompoundDrawables(drawable, null, null, null);
        mAnimation = (AnimationDrawable) tvTdPtrLoadMoreTxt.getCompoundDrawables()[0];
        if (!mAnimation.isRunning()) {
            mAnimation.start();
        }
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {
        tvTdPtrLoadMoreTxt.setText("加载完成");
        //停止动画
        if (mAnimation != null && mAnimation.isRunning()) {
            mAnimation.stop();
        }
        Drawable drawable = getResources().getDrawable(R.drawable.loading_00);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvTdPtrLoadMoreTxt.setCompoundDrawables(drawable, null, null, null);
        mAnimation = null;
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        if (status == PtrFrameLayout.PTR_STATUS_PREPARE) {
            if (ptrIndicator.getCurrentPercent() < ptrIndicator.getRatioOfHeaderToHeightRefresh()) {
                tvTdPtrLoadMoreTxt.setText("上拉可以加载更多");
            } else {
                tvTdPtrLoadMoreTxt.setText("释放即可加载更多");
            }
        }

    }
}
