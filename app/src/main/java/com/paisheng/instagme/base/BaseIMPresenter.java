package com.paisheng.instagme.base;

import com.paisheng.lib.mvp.network.INetworkView;
import com.paisheng.lib.mvp.network.NetworkPresenter;
import com.paisheng.lib.network.IRequestManager;
import com.paisheng.lib.network.RequestCall;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: yuanbaining
 * @Filename: BaseIMPresenter
 * @Description:    Presenter中间层，预留
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/25 16:02
 */

public class BaseIMPresenter<T extends INetworkView> extends NetworkPresenter<T> {

}
