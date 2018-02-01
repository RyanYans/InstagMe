package com.paisheng.instagme.business.home.hometab.contract;

import com.paisheng.instagme.base.IBaseView;
import com.paisheng.instagme.business.home.hometab.model.entity.PictResultInfo;
import com.paisheng.instagme.business.home.hometab.model.entity.PosterResultInfo;

import java.util.List;

/**
 * @author: yuanbaining
 * @Filename: IPictFragmentContract
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/30 17:07
 */


public interface IPictFragmentContract {
    interface IView extends IBaseView {
        /**
         *<br> Description: 加载数据
         *<br> Author:      yuanbaining
         *<br> Date:        2018/1/31 9:48
         */
        void loadPicsData();

        /**
         *<br> Description: 显示加载的数据
         *<br> Author:      yuanbaining
         *<br> Date:        2018/1/31 9:48
         */
        void showPicsData(int taskId, List<PictResultInfo.ResultsBean> listData, int total);

        /**
         *<br> Description: 无网络状态下显示
         *<br> Author:      yuanbaining
         *<br> Date:        2018/1/31 9:49
         */
        void showPicsDataNotNet();

        /**
         *<br> Description: 加载失败显示
         *<br> Author:      yuanbaining
         *<br> Date:        2018/1/31 9:50
         */
        void showPicsDataFailure();
    }

    interface IPresenter {
        /**
         *<br> Description: 加载图片数据
         *<br> Author:      yuanbaining
         *<br> Date:        2018/2/1 19:22
         * @param taskId    加载标识
         * @param pageIndex 当前页面
         * @param tips
         */
        void loadPictPics(final int taskId, int pageIndex, String tips);
    }
}
