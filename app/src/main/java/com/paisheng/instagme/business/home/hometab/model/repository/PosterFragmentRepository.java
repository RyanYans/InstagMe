package com.paisheng.instagme.business.home.hometab.model.repository;

import com.paisheng.instagme.base.IBaseView;
import com.paisheng.instagme.business.home.hometab.constant.PosterMethodConstant;
import com.paisheng.instagme.business.home.hometab.model.entity.PosterResultInfo;
import com.paisheng.instagme.common.netcallback.ICommonRequestCallback;
import com.paisheng.instagme.network.IMHttpUtils;
import com.paisheng.instagme.network.callback.IMCallback;
import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.network.exception.ApiException;

import java.util.HashMap;

/**
 * @author: yuanbaining
 * @Filename: PosterFragmentRepository
 * @Description:    帖子页面M层数据访问层
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/30 17:10
 */

public class PosterFragmentRepository {
    /**
     *<br> Description: 网络加载帖子信息
     *<br> Author:      yuanbaining
     *<br> Date:        2018/2/1 19:11
     * @param baseView  父布局
     * @param callback  回掉接口
     * @param page      当前页
     */
    public static void loadListData(IBaseView baseView, final ICommonRequestCallback<PosterResultInfo> callback,
                                    int page) {
        HashMap<String, String> params = new HashMap<>();
        params.put("sars", "index/thread");
        params.put("nexttime", page + "");

        IMHttpUtils.postIM(PosterMethodConstant.POSTER_LIST_DATA_METHOD, params)
                .execute(new IMCallback<PosterResultInfo>(baseView) {
                    @Override
                    public void onSuccess(PosterResultInfo response) {
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
