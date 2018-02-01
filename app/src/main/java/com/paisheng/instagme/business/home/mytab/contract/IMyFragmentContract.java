package com.paisheng.instagme.business.home.mytab.contract;

import android.app.Activity;

import com.paisheng.instagme.base.IBaseView;
import com.paisheng.instagme.business.home.mytab.model.entity.MyResultInfo;

/**
 * @author: yuanbaining
 * @Filename: MyFragmentContract
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/30 19:27
 */

public interface IMyFragmentContract {
    interface IView extends IBaseView {
        /**
         * <br> Description: 展示数据
         * <br> Author:      yuanbaining
         * <br> Date:        2018/1/31 16:30
         */
        void showUserData(MyResultInfo info);

        /**
         *<br> Description: 显示登陆对话框
         *<br> Author:      yuanbaining
         *<br> Date:        2018/2/1 19:30
         */
        void showLoginDialog(String title, String content);
    }

    interface IPresenter {
        /**
         *<br> Description: 加载数据
         *<br> Author:      yuanbaining
         *<br> Date:        2018/1/31 16:29
         */
        void loadUserData();

        /**
         *<br> Description: 退出登陆
         *<br> Author:      yuanbaining
         *<br> Date:        2018/1/31 16:29
         */
        void userUnLogin();

        /**
         *<br> Description: 用户登陆
         *<br> Author:      yuanbaining
         *<br> Date:        2018/1/31 16:30
         */
        void userLogin();
    }
}
