package com.paisheng.instagme.common.netcallback;

import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.network.exception.ApiException;

/**
 * <br> ClassName:   CommonRequestCallback
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/8/8 9:48
 */

public class CommonRequestCallback<T> implements ICommonRequestCallback<T> {
    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onFailure(RequestCall requestCall, ApiException e) {

    }
}
