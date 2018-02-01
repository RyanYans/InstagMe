package com.paisheng.instagme.business.home.main.contract;

import com.paisheng.instagme.base.IBaseView;

/**
 * @author: yuanbaining
 * @Filename: IMainActivityContract
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/26 9:14
 */


public interface IMainActivityContract {
    interface IView extends IBaseView{

    }

    interface IPresenter {
        void exit();
    }
}
