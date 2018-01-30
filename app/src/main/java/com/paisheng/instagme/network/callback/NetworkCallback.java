package com.paisheng.instagme.network.callback;

import com.alibaba.fastjson.JSON;
import com.paisheng.lib.network.callback.AbstractCallback;

import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import okhttp3.Response;

/**
 * <br> ClassName:   NetworkCallback
 * <br> Description: 网络请求回调，数据解析
 * <br>
 * <br> Author:      liaoshengjian
 * <br> Date:        2017/5/18 17:10
 */
public abstract class NetworkCallback<T> extends AbstractCallback<T> {

    @Override
    public T parseResponse(Call call, Response response) throws Exception {
        String responseString = response.body().string();

        Class<? super T> rawType;
        rawType = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        T data = (T) JSON.parseObject(responseString, rawType);

        return data;
    }
}