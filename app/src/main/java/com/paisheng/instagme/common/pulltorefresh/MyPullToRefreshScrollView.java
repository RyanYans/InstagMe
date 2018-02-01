package com.paisheng.instagme.common.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;

import com.paisheng.lib.vendor.ptr.PtrUIHandler;
import com.paisheng.lib.widget.pulltorefresh.PullToRefreshScrollView;

/**
 * <br> ClassName:   MyPullToRefreshScrollView
 * <br> Description: 团贷业务 下拉刷新 ScrollView
 * <br>
 * <br> Author:      Administrator
 * <br> Date:        2017/7/12 17:52
 */
public class MyPullToRefreshScrollView extends PullToRefreshScrollView {

    private CommonPtrDefaultHeader myPtrDefaultHeader;

    public MyPullToRefreshScrollView(Context context) {
        super(context);
    }

    public MyPullToRefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDefaultLoadingFooterView();
    }

    @Override
    public void setDefaultLoadingHeaderView() {
        myPtrDefaultHeader = new CommonPtrDefaultHeader(getContext());
        myPtrDefaultHeader.setheaderHeightUpdateListener(new CommonPtrDefaultHeader.HeaderHeightUpdateListener() {
            @Override
            public void headerHeightUpdate() {
                refreshHeaderHeight();
            }
        });
        setLoadingHeaderView(myPtrDefaultHeader);
    }

    @Override
    public void setDefaultLoadingFooterView() {
        setLoadingFooterView(new CommonPtrDefaultFooter(getContext()));
    }

    @Override
    public void showLoadingHeaderImg() {
        if (myPtrDefaultHeader != null) {
            myPtrDefaultHeader.showIvTDPtrLoadingHeader();
        }
    }

    @Override
    public void setLoadingFooterView(PtrUIHandler ptrUIHandler) {
        super.setLoadingFooterView(ptrUIHandler);
    }
}
