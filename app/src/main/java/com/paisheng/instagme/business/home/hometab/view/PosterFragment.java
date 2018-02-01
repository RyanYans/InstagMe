package com.paisheng.instagme.business.home.hometab.view;

import com.paisheng.instagme.R;
import com.paisheng.instagme.base.AbstractIMFragment;
import com.paisheng.instagme.business.home.hometab.contract.IPosterFragmentContract;
import com.paisheng.instagme.business.home.hometab.model.entity.PosterResultInfo;
import com.paisheng.instagme.business.home.hometab.presenter.PosterFragmentPresenter;
import com.paisheng.instagme.common.pulltorefresh.MyPullToRefreshListView;
import com.paisheng.instagme.utils.TimeUtil;
import com.paisheng.lib.util.ToastUtil;
import com.paisheng.lib.view.ViewHolder;
import com.paisheng.lib.widget.pulltorefresh.OnPullListActionListener;
import com.paisheng.lib.widget.pulltorefresh.PullToRefreshBaseView;
import java.util.List;
import butterknife.BindView;
import butterknife.Unbinder;


/**
 * @author: yuanbaining
 * @Filename: PosterFragment
 * @Description:    帖子界面Fragment
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/29 12:44
 */

public class PosterFragment extends AbstractIMFragment<PosterFragmentPresenter>
        implements IPosterFragmentContract.IView, PullToRefreshBaseView.OnLastPageHintListener {

    @BindView(R.id.pullToRefreshListView)
    MyPullToRefreshListView<PosterResultInfo.DataBean> mPullToRefreshListView;
    private PosterFragmentPresenter mPosterFragmentPresenter;

    @Override
    protected PosterFragmentPresenter createPresenter() {
        mPosterFragmentPresenter = new PosterFragmentPresenter();
        return mPosterFragmentPresenter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_poster;
    }

    /**
     *<br> Description: 先初始化界面布局，再进行数据加载
     *<br> Author:      yuanbaining
     *<br> Date:        2018/2/1 19:04
     */
    @Override
    protected void lazyLoad() {
        initView();
        initData();
    }

    /**
     *<br> Description: 初始化下拉刷新、上拉加载List
     *<br> Author:      yuanbaining
     *<br> Date:        2018/2/1 19:02
     */
    private void initView() {
        if (mPullToRefreshListView != null) {
            mPullToRefreshListView.setOnLastPageHint(this);

            mPullToRefreshListView.setOnPullListActionListener(new OnPullListActionListener<PosterResultInfo.DataBean>() {
                @Override
                public void loadData(int viewId, int taskId, int pageIndex, String tips) {
                    if (mPosterFragmentPresenter != null) {
                        mPosterFragmentPresenter.loadPosterList(taskId, pageIndex, tips);
                    }
                }

                @Override
                public void clickItem(int viewId, PosterResultInfo.DataBean item, int position) {
                    ToastUtil.showToast("你点击了第" + (position + 1) + "条数据", getMainLayout());
                }

                @Override
                public void createListItem(int viewId, ViewHolder holder, PosterResultInfo.DataBean currentItem,
                                           List<PosterResultInfo.DataBean> list, int position) {
                    if (currentItem != null) {
                        long timeSecond = Long.parseLong(currentItem.getDateline());

                        holder.setText(R.id.tv_item_name, currentItem.getUsername());
                        holder.setText(R.id.tv_item_timer, TimeUtil.parseDateTime(timeSecond));
                        holder.setText(R.id.tv_item_subject, currentItem.getSubject());
                        holder.setText(R.id.tv_item_message, currentItem.getMessage());
                        holder.setText(R.id.tv_item_reply, currentItem.getReplynum() + "");
                    }
                }
                @Override
                public void onRefreshComplete() {

                }
            });
        }
    }

    private void initData() {
        loadListData();
    }

    @Override
    public void onLastPageHint() {
        ToastUtil.showToast("已经是最后一页", getMainLayout());
    }

    @Override
    public void loadListData() {
        if (mPullToRefreshListView != null) {
            mPullToRefreshListView.loadData();
        }
    }

    @Override
    public void showListData(int taskId, List<PosterResultInfo.DataBean> listData, int total) {
        if (mPullToRefreshListView != null) {
            mPullToRefreshListView.show10Data(taskId, listData, total, R.layout.item_poster_list);
        }
    }

    @Override
    public void showListDataNotNet() {
        ToastUtil.showToast("加载失败，无网络", getMainLayout());
    }

    @Override
    public void showListDataFailure() {
        ToastUtil.showToast("数据加载失败~", getMainLayout());
    }
}
