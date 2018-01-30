package com.paisheng.instagme.common.pulltorefresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
 * @Description:    自定义默认LoadingHeaderView
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2017/1/16 16:35
 */
public class CommonPtrDefaultHeader extends LinearLayout implements PtrUIHandler {

    private final float HEADER_SCALE_POSITION = 80f;
    private float REAL_HEADER_SCALE_POSITION;
    {
        float scale = getResources().getDisplayMetrics().density;
        REAL_HEADER_SCALE_POSITION = HEADER_SCALE_POSITION * scale + 0.5f;
    }

    private ImageView ivTDPtrLoadingHeader;
//    private ImageView ivTDPtrLoadingImg;
    private TextView tvTdPtrLoadingTxt;

    private float pivotX = 0;
    private float pivotY = 0;

    /**
     * 动画
     */
    private AnimationDrawable mAnimation;

    public CommonPtrDefaultHeader(Context context) {
        super(context, null);
        initHeadView();
    }

    public CommonPtrDefaultHeader(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initHeadView();
    }

    public CommonPtrDefaultHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeadView();
    }

    private void initHeadView() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        setPadding(UiUtil.dp2px(24), UiUtil.dp2px(12)
                , UiUtil.dp2px(24), UiUtil.dp2px(12));
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ptr_common_default_header, this,true);
        this.ivTDPtrLoadingHeader = (ImageView) view.findViewById(R.id.ivTDPtrLoadingHeader);
//        this.ivTDPtrLoadingImg = (ImageView) view.findViewById(R.id.ivTDPtrLoadingImg);
        this.tvTdPtrLoadingTxt = (TextView) view.findViewById(R.id.tvTdPtrLoadingTxt);
    }

    public void showIvTDPtrLoadingHeader() {
        if (ivTDPtrLoadingHeader != null && ivTDPtrLoadingHeader.getVisibility() != View.VISIBLE) {
            // 设置顶部刷新图片
        }
    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        if (ivTDPtrLoadingHeader.getVisibility() == View.VISIBLE) {
            pivotX = ivTDPtrLoadingHeader.getWidth()/2;
            pivotY = ivTDPtrLoadingHeader.getHeight();
            ivTDPtrLoadingHeader.setPivotX(pivotX);
            ivTDPtrLoadingHeader.setPivotY(pivotY);
            ivTDPtrLoadingHeader.setScaleX(1);
            ivTDPtrLoadingHeader.setScaleY(1);
        }
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        tvTdPtrLoadingTxt.setText("加载中...");
//        ivTDPtrLoadingHeader.setScaleX(1);
//        ivTDPtrLoadingHeader.setScaleY(1);
        Drawable drawable = getResources().getDrawable(R.drawable.progress_loading_anim);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        tvTdPtrLoadingTxt.setCompoundDrawables(drawable,null,null,null);
        mAnimation = (AnimationDrawable) tvTdPtrLoadingTxt.getCompoundDrawables()[0];
        if (!mAnimation.isRunning()) {
            mAnimation.start();
        }
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {
        tvTdPtrLoadingTxt.setText("加载完成");
//        ivTDPtrLoadingHeader.setScaleX(1);
//        ivTDPtrLoadingHeader.setScaleY(1);
        //停止动画
        if (mAnimation != null && mAnimation.isRunning()) {
            mAnimation.stop();
        }
        Drawable drawable = getResources().getDrawable(R.drawable.loading_00);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        tvTdPtrLoadingTxt.setCompoundDrawables(drawable,null,null,null);
        mAnimation = null;
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        if (status == PtrFrameLayout.PTR_STATUS_PREPARE) {
            if(headerHeightUpdateListener != null){
                headerHeightUpdateListener.headerHeightUpdate();
            }

            //logo设置
//            ivTDPtrLoadingHeader.setAlpha(ptrIndicator.getCurrentPercent());

            if (ivTDPtrLoadingHeader.getVisibility() == View.VISIBLE) {
                if (ptrIndicator.getCurrentPosY() <= REAL_HEADER_SCALE_POSITION) {
                    if (pivotX == 0 && pivotY == 0) {
                        pivotX = ivTDPtrLoadingHeader.getWidth()/2;
                        pivotY = ivTDPtrLoadingHeader.getHeight();
                        ivTDPtrLoadingHeader.setPivotX(pivotX);
                        ivTDPtrLoadingHeader.setPivotY(pivotY);
                    }
                    float scalePos = ptrIndicator.getCurrentPosY()/ REAL_HEADER_SCALE_POSITION;
                    ivTDPtrLoadingHeader.setScaleX(scalePos);
                    ivTDPtrLoadingHeader.setScaleY(scalePos);
                }else {
                    ivTDPtrLoadingHeader.setScaleX(1);
                    ivTDPtrLoadingHeader.setScaleY(1);
                }
            }

            if (ptrIndicator.getCurrentPercent() < ptrIndicator.getRatioOfHeaderToHeightRefresh()) {
                tvTdPtrLoadingTxt.setText("下拉可以刷新");
            } else {
                tvTdPtrLoadingTxt.setText("松开立即刷新");
            }
        }

    }



    private HeaderHeightUpdateListener headerHeightUpdateListener;
    public void setheaderHeightUpdateListener(HeaderHeightUpdateListener headerHeightUpdateListener) {
        this.headerHeightUpdateListener = headerHeightUpdateListener;
    }

    /**
     *  HeaderView loading Image 需要网络加载，导致HeaderView 高度开始获取错误
     *  需要重新通知 PtrFrameLayout 重新获取 HeaderView height
     */
    public interface HeaderHeightUpdateListener{
        void headerHeightUpdate();
    }
}
