package com.paisheng.instagme.business.home.hometab.presenter;

import com.paisheng.instagme.base.BaseIMPresenter;
import com.paisheng.instagme.business.home.hometab.contract.IPosterFragmentContract;
import com.paisheng.instagme.business.home.hometab.model.entity.PosterResultInfo;
import com.paisheng.instagme.business.home.hometab.model.repository.PosterFragmentRepository;
import com.paisheng.instagme.business.home.hometab.view.PosterFragment;
import com.paisheng.instagme.common.netcallback.ICommonRequestCallback;
import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.network.exception.ApiException;


/**
 * @author: yuanbaining
 * @Filename: PosterFragmentPresenter
 * @Description:    帖子界面P层控制
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/30 17:50
 */

public class PosterFragmentPresenter extends BaseIMPresenter<PosterFragment> implements IPosterFragmentContract.IPresenter{
    @Override
    public void loadPosterList(final int taskId, int pageIndex, String tips) {
        getView().showLoading("加载中..");
        PosterFragmentRepository.loadListData(getView(), new ICommonRequestCallback<PosterResultInfo>() {
            @Override
            public void onSuccess(PosterResultInfo posterResultInfo) {
                getView().showListData(taskId, posterResultInfo.getData(), posterResultInfo.getData().size());
                getView().dismissLoading();
            }

            @Override
            public void onFailure(RequestCall requestCall, ApiException e) {
                getView().showListDataFailure();
                getView().dismissLoading();
            }
        }, pageIndex);
    }
}
