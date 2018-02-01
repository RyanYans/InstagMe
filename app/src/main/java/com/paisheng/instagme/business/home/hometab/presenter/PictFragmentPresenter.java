package com.paisheng.instagme.business.home.hometab.presenter;

import com.paisheng.instagme.base.BaseIMPresenter;
import com.paisheng.instagme.business.home.hometab.contract.IPictFragmentContract;
import com.paisheng.instagme.business.home.hometab.model.entity.PictResultInfo;
import com.paisheng.instagme.business.home.hometab.model.repository.PictFragmentRepository;
import com.paisheng.instagme.common.netcallback.ICommonRequestCallback;
import com.paisheng.lib.mvp.network.NetworkPresenter;
import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.network.exception.ApiException;

/**
 * @author: yuanbaining
 * @Filename: PictFragmentPresenter
 * @Description:    图片界面P层控制
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/30 17:03
 */

public class PictFragmentPresenter extends BaseIMPresenter<IPictFragmentContract.IView>
        implements IPictFragmentContract.IPresenter{

    @Override
    public void loadPictPics(final int taskId, int pageIndex, String tips) {
        getView().showLoading("加载中..");
        PictFragmentRepository.loadListData(getView(), new ICommonRequestCallback<PictResultInfo>() {
            @Override
            public void onSuccess(PictResultInfo pictResultInfo) {
                getView().showPicsData(taskId, pictResultInfo.getResults(), pictResultInfo.getResults().size());
                getView().dismissLoading();
            }

            @Override
            public void onFailure(RequestCall requestCall, ApiException e) {
                getView().showPicsDataFailure();
                getView().dismissLoading();
            }
        }, pageIndex);
    }
}
