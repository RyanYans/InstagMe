package com.paisheng.instagme.common.netcallback;

import com.paisheng.lib.network.RequestCall;
import com.paisheng.lib.network.exception.ApiException;

/**
 * <br> ClassName:   ICommonRequestCallback
 * <br> Description: 网络请求统一P层接口回掉
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/8/8 9:43
 */

public interface ICommonRequestCallback<T> {
    /**
     * <br> Description: 请求成功
     * <br> Author:      谢文良
     * <br> Date:        2017/8/8 9:47
     *
     * @param t T
     */
    void onSuccess(T t);

    /**
     * <br> Description: 请求失败
     * <br> Author:      谢文良
     * <br> Date:        2017/8/8 9:47
     *
     * @param requestCall RequestCall
     * @param e           ApiException
     */
    void onFailure(RequestCall requestCall, ApiException e);
}
