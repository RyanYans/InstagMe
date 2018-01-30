package com.paisheng.instagme.base;

import com.paisheng.lib.network.IRequestManager;
import com.paisheng.lib.network.RequestCall;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: yuanbaining
 * @Filename: BasePresenter
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/25 16:02
 */


public class BasePresenter<T> implements IRequestManager<RequestCall> {

    private BlockingQueue<RequestCall> mCallBlockingQueue = new LinkedBlockingQueue<>();

    /*** 关联view的弱引用 ***/
    private Reference<T> mViewRef;

    public void attachView(T view) {
        mViewRef = new WeakReference<>(view);
    }

    public T getView() {
        return mViewRef.get();
    }

    @Override
    public void addCalls(RequestCall call) {
        //call.getCall() == null 同样加入，重新加载会重新生成call
        if (call != null && !mCallBlockingQueue.contains(call)) {
            mCallBlockingQueue.add(call);
        }
    }

    @Override
    public void removeCall(RequestCall call) {
        if (mCallBlockingQueue != null) {
            synchronized (mCallBlockingQueue) {
                if (mCallBlockingQueue != null) {
                    mCallBlockingQueue.remove(call);
                }
            }
        }
    }

    @Override
    public void removeAllCalls() {
        if (mCallBlockingQueue != null) {
            synchronized (mCallBlockingQueue) {
                if (mCallBlockingQueue != null) {
                    for (RequestCall requestCall : mCallBlockingQueue) {
                        if (requestCall != null && requestCall.getCall() != null) {
                            requestCall.getCall().cancel();
                        }
                    }
                    mCallBlockingQueue.clear();
                    mCallBlockingQueue = null;
                }
            }
        }
    }
}
