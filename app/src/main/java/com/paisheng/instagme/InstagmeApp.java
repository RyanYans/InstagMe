package com.paisheng.instagme;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.paisheng.lib.network.Smart;


/**
 * @author: yuanbaining
 * @Filename: FirstApplication
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/25 16:31
 */


public class InstagmeApp extends Application {

    private static InstagmeApp INSTANCE;

    public static Context getContext() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        initARouter();
        initNetwork();
    }

    private void initARouter() {
        ARouter.init(this);
    }

    private void initNetwork() {
        Smart.init(this);
    }
}
