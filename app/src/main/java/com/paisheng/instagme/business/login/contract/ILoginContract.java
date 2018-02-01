package com.paisheng.instagme.business.login.contract;


import com.paisheng.instagme.base.IBaseView;

/**
 * @author: yuanbaining
 * @Filename: ILoginActivityContract
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/24 16:20
 */

public interface ILoginContract {
    interface IView extends IBaseView {
    }

    interface IPresenter {
        /**
         *<br> Description: 正常登陆
         *<br> Author:      yuanbaining
         *<br> Date:        2018/1/31 16:19
         */
        void toLoginNet(String email, String password);

        /**
         *<br> Description: 跳过登陆
         *<br> Author:      yuanbaining
         *<br> Date:        2018/1/31 16:20
         */
        void toLoginSkip();
    }

}
