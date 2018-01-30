package com.paisheng.instagme.common.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.paisheng.lib.widget.pulltorefresh.OnPullListActionListener;
import com.paisheng.lib.widget.pulltorefresh.PullToRefreshRecyclerView;
import com.paisheng.lib.widget.reloadview.PageTips;
import com.paisheng.lib.widget.reloadview.ReloadTipsView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liaoshengjian
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2017/8/3 11:10
 */
public class MyPullToRefreshListView<T> extends PullToRefreshRecyclerView<T>
        implements ReloadTipsView.LoadTipsListener {
    /***  加载类型(刷新) ***/
    private static final int REFRESH_ID = 0;
    /***  加载类型(加载更多) ***/
    private static final int MORE_TASK_ID = 1;
    /***  加载页面条数(10) ***/
    public static final int PAGE_SIZE_10 = 10;
    /***  加载页面条数(20) ***/
    public static final int PAGE_SIZE = 20;

    private ReloadTipsView mReloadTipsView;
    private PageTips mPageTips;

    private CommonPtrDefaultHeader myPtrDefaultHeader;

    @Override
    public void onInitMainView() {
        mReloadTipsView = new ReloadTipsView(getContext());
        mReloadTipsView.setLayoutParams(new ReloadTipsView.LayoutParams(-1, -1));
        mReloadTipsView.setVisibility(View.GONE);
        mReloadTipsView.setOnReloadDataListener(this);
        this.addView(mReloadTipsView, 0);
    }

    public MyPullToRefreshListView(Context context, OnPullListActionListener<T> mPullListActionListener) {
        super(context, mPullListActionListener);
        initData();
    }

    public MyPullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }


    public MyPullToRefreshListView(Context context) {
        super(context);
        initData();
    }

    private void initData() {
        loadRefreshId = REFRESH_ID;
        loadMoreTaskId = MORE_TASK_ID;
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



    /**
     * <br> Description: 第一次加载数据
     * <br> Author:      谢文良
     * <br> Date:        2017/8/8 15:36
     */
    public void loadData() {
        loadRefreshData(loadRefreshId, true);
    }

    /**
     * <br> Description: 刷新
     * <br> Author:      谢文良
     * <br> Date:        2017/8/8 15:36
     */
    public void reloadData() {
        loadRefreshData(loadRefreshId, false);
    }

    /**
     * <br> Description: 显示数据(10条)
     * <br> Author:      谢文良
     * <br> Date:        2017/8/8 15:36
     */
    public boolean show10Data(int taskId, List<T> listData, int total, int itemLayoutId) {
        return showDataByPageSize(taskId, listData, total, itemLayoutId, PAGE_SIZE_10);
    }

    /**
     * <br> Description: 显示数据
     * <br> Author:      谢文良
     * <br> Date:        2017/8/8 15:37
     */
    public boolean showData(int taskId, List<T> listData, int total, int itemLayoutId) {
        return showDataByPageSize(taskId, listData, total, itemLayoutId, PAGE_SIZE);
    }

    private boolean showDataByPageSize(int taskId, List<T> listData, int total, int itemLayoutId, int pageSize) {
        try {
            onRefreshComplete();

            if (mList == null) {
                mList = new ArrayList<T>();
            }
            if (taskId == loadRefreshId) {
                mList.clear();
            }
            if (listData == null || listData.size() <= 0) {
                notifyDataSetChanged();
                return showNoDataView();
            }
            mTotalCount = (total - 1) / pageSize + 1;
             mReloadTipsView.setVisibility(View.GONE);
            mPageIndex++;
            mList.addAll(listData);

            showAllData(mList, itemLayoutId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean showNoDataView() {
        // 情感图逻辑 暂缺
        mReloadTipsView.setVisibility(View.VISIBLE);
        if (mList == null || mList.size() <= 0) {
            if (mPageTips != null) {
                mReloadTipsView.showCustomEmptyTips(mPageTips);
            } else {
                mReloadTipsView.showEmptyTips();
            }
            return true;
        } else {
            // 情感图逻辑 暂缺
            mReloadTipsView.showFailureTips();
            return false;
        }
    }

    public void setEmptyTips(PageTips pageTips) {
        mPageTips = pageTips;
    }

    public void showFailure() {
        onRefreshComplete();
        mReloadTipsView.setVisibility(View.VISIBLE);
        mReloadTipsView.showFailureTips();
    }

    public void showNoNetworkTips() {
        onRefreshComplete();
        mReloadTipsView.setVisibility(View.VISIBLE);
        mReloadTipsView.showNoNetworkTips();
    }

    /**
     * 重新加载
     */
    @Override
    public void clickReloadData() {
        mReloadTipsView.setVisibility(View.VISIBLE);
        mReloadTipsView.showProgressBar();
        reloadData();
    }

}
