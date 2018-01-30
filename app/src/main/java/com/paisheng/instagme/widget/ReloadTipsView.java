package com.paisheng.instagme.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paisheng.instagme.R;
import com.paisheng.lib.widget.reloadview.PageTips;

/**
 * @author: liaoshengjian
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2017/8/8 18:49
 */
public class ReloadTipsView extends RelativeLayout {

    private LinearLayout layLMain;
    private View mView;
    private ImageView mSpinnerImageView;
    private TextView mTvTips, mTvTipsDesc1;
    private ImageView mImgLogo;

    public ReloadTipsView(Context context) {
        super(context);
        init(context);
    }

    public ReloadTipsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mView = LayoutInflater.from(context).inflate(R.layout.view_rtv_load_tips, this, true);
        layLMain = (LinearLayout) mView.findViewById(R.id.layLMain);
        mImgLogo = (ImageView) mView.findViewById(R.id.imgLogo);
        mTvTips = (TextView) mView.findViewById(R.id.tvTips);
        mTvTipsDesc1 = (TextView) mView.findViewById(R.id.tvTipsDesc1);
        mSpinnerImageView = (ImageView) mView.findViewById(R.id.spinnerImageView);
    }

    private void showTips() {
        mImgLogo.setVisibility(View.VISIBLE);
        mTvTips.setVisibility(View.VISIBLE);
        mTvTipsDesc1.setVisibility(View.VISIBLE);

        mView.setVisibility(View.VISIBLE);
        mSpinnerImageView.setVisibility(View.GONE);
    }

    public void showFailureTips() {
        showTips();
        mImgLogo.setImageResource(R.drawable.rtv_crash);
        mTvTips.setText(R.string.rtv_service_tips);
    }

    public void showEmptyTips() {
        showTips();
        mImgLogo.setImageResource(R.drawable.rtv_empty_investment_norecord);
        mTvTips.setText(R.string.rtv_not_data);
    }

    public void showEmptyTips(int drawable) {
        showTips();
        if (drawable > 0) {
            mImgLogo.setImageResource(drawable);
        } else {
            mImgLogo.setImageResource(R.drawable.rtv_empty_investment_norecord);
        }
        mTvTips.setText(R.string.rtv_not_data);
    }


    public void showNoNetworkTips() {
        showTips();
        mImgLogo.setImageResource(R.drawable.rtv_empty_investment_nonetwork);
        mTvTips.setText(R.string.rtv_not_net);
    }

    public void showCustomEmptyTips(PageTips pageTips) {
        if (pageTips == null || TextUtils.isEmpty(pageTips.getTips())) {
            showEmptyTips();
        }

        if (pageTips.getIconResid() > 0) {
            mImgLogo.setVisibility(View.VISIBLE);
            mImgLogo.setImageResource(pageTips.getIconResid());
        } else {
            mImgLogo.setVisibility(View.GONE);
        }
        mTvTips.setVisibility(View.VISIBLE);
        mTvTips.setText(pageTips.getTips());
        mTvTipsDesc1.setVisibility(View.VISIBLE);

        mView.setVisibility(View.VISIBLE);
        mSpinnerImageView.setVisibility(View.GONE);
    }


    /**
     * 设置重新加载监听
     *
     * @param listener void
     * @author longluliu
     * @date 2014-4-2 下午4:32:58
     */
    public void setOnReloadDataListener(final LoadTipsListener listener) {
        if (listener != null && layLMain != null) {
            layLMain.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    showProgressBar();
                    listener.clickReloadData();
                }
            });
        }
    }

    /**
     * 显示load
     * void
     *
     * @author longluliu
     * @date 2014-4-2 下午4:33:48
     */
    public void showProgressBar() {
        mView.setVisibility(View.VISIBLE);
        mSpinnerImageView.setVisibility(View.VISIBLE);
        AnimationDrawable spinner = (AnimationDrawable) mSpinnerImageView.getBackground();
        spinner.start();

        mTvTips.setVisibility(View.GONE);
        mTvTipsDesc1.setVisibility(View.GONE);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        AnimationDrawable spinner = (AnimationDrawable) mSpinnerImageView.getBackground();
        spinner.stop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    /**
     * 隐藏
     * void
     *
     * @author longluliu
     * @date 2014-4-2 下午4:33:48
     */
    public void goneView() {
        mView.setVisibility(View.GONE);
    }

    public interface LoadTipsListener {
        void clickReloadData();
    }
}
