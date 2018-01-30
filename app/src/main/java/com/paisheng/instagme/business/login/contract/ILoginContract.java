package com.paisheng.instagme.business.login.contract;


import com.paisheng.instagme.base.IBaseView;

/**
 * @author: yuanbaining
 * @Filename: ILoginActivityContract
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/24 16:20
 */


public class ILoginContract {
    public interface IView extends IBaseView {

    }

    public interface IPresenter {
        void toLoginNet(String email, String password);

        void toLoginSkip();
    }

}
