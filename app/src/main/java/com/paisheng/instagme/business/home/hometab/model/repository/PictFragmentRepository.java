package com.paisheng.instagme.business.home.hometab.model.repository;

import com.paisheng.instagme.base.IBaseView;
import com.paisheng.instagme.business.home.hometab.constant.PictMethodConstant;
import com.paisheng.instagme.business.home.hometab.model.entity.PictResultInfo;
import com.paisheng.instagme.common.netcallback.ICommonRequestCallback;
import com.paisheng.instagme.network.IMHttpUtils;
import com.paisheng.instagme.network.callback.IMCallback;
import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.network.exception.ApiException;

/**
 * @author: yuanbaining
 * @Filename: PictFragmentRepository
 * @Description:    图片页面M层数据访问层
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/30 17:09
 */


public class PictFragmentRepository {
    private static final int BASE_PAGE = 3;

    /**
     *<br> Description: 网络加载图片信息
     *<br> Author:      yuanbaining
     *<br> Date:        2018/2/1 19:11
     * @param baseView  父布局
     * @param callback  回掉接口
     * @param page      当前页
     */
    public static void loadListData(IBaseView baseView, final ICommonRequestCallback<PictResultInfo> callback,
                                    int page) {
        String jointMethod = PictMethodConstant.PICT_LIST_DATA_METHOD + (BASE_PAGE + page);
        IMHttpUtils.GetIM4Pics(jointMethod, null)
                .execute(new IMCallback<PictResultInfo>(baseView) {
                    @Override
                    public void onSuccess(PictResultInfo response) {
                        callback.onSuccess(response);
                    }
                    @Override
                    public void onFailure(RequestCall requestCall, ApiException e) {
                        super.onFailure(requestCall, e);
                        callback.onFailure(requestCall, e);
                    }
                });
    }
}
