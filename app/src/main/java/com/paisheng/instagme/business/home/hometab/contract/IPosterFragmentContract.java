package com.paisheng.instagme.business.home.hometab.contract;

import com.paisheng.instagme.base.IBaseView;
import com.paisheng.instagme.business.home.hometab.model.entity.PosterResultInfo;

import java.util.List;

/**
 * @author: yuanbaining
 * @Filename: IPosterFragmentContract
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/30 17:08
 */

public interface IPosterFragmentContract {
    interface IView extends IBaseView {
        /**
         *<br> Description: 加载数据
         *<br> Author:      yuanbaining
         *<br> Date:        2018/1/31 9:48
         */
        void loadListData();

        /**
         *<br> Description: 显示加载的数据
         *<br> Author:      yuanbaining
         *<br> Date:        2018/1/31 9:48
         */
        void showListData(int taskId, List<PosterResultInfo.DataBean> listData, int total);

        /**
         *<br> Description: 无网络状态下显示
         *<br> Author:      yuanbaining
         *<br> Date:        2018/1/31 9:49
         */
        void showListDataNotNet();

        /**
         *<br> Description: 加载失败显示
         *<br> Author:      yuanbaining
         *<br> Date:        2018/1/31 9:50
         */
        void showListDataFailure();

    }

    interface IPresenter {
        /**
         *<br> Description: 帖子信息数据加载
         *<br> Author:      yuanbaining
         *<br> Date:        2018/2/1 19:07
         */
        void loadPosterList(int taskId, int pageIndex, String tips);
    }

}
