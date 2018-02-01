package com.paisheng.instagme;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.paisheng.instagme.network.cookie.CookieJarImpl;
import com.paisheng.instagme.network.cookie.store.MemoryCookieStore;
import com.paisheng.lib.network.Smart;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;


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
        ARouter.openLog();      // 打印日志
        ARouter.openDebug();    // 开启调试模式

        ARouter.init(this);
    }

    private void initNetwork() {
        Smart.init(this);

        //设置Http缓存
        Cache cache = new Cache(new File(InstagmeApp.getContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 10);
        //设置Cookie缓存
        CookieJarImpl cookieJar = new CookieJarImpl(new MemoryCookieStore());

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .cache(cache)
                .cookieJar(cookieJar)
                .connectTimeout(10, TimeUnit.SECONDS);

        Smart.initOkHttp(builder);
    }
}
