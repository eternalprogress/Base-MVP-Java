package com.joker.basemvp.utils;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joker.basemvp.app.BaseApplication;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by @ZhangBo
 * on @2017/12.
 */

public class Factory {
    private static final Factory instance;
    //线程池
    private final Executor executor;
    //全局的Gson
    private final Gson gson;

    static {
        instance = new Factory();
    }

    private Factory() {
        //新建一个4个线程的线程池
        executor = Executors.newFixedThreadPool(4);
        gson = new GsonBuilder()
                //设置时间格式
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .create();
    }

    /**
     * 返回一个全局的Application
     * @return Application
     */
    public static Application app() {
        return BaseApplication.getInstance();
    }

    /**
     * 异步运行的方法
     * @param runnable Runnable
     */
    public static void runOnAsync(Runnable runnable) {
        //拿到单例,拿到线程池,异步执行
        instance.executor.execute(runnable);
    }

    /**
     * 返回一个全局的json 在这里可以进行全局的Gson的初始化
     * @return Gson
     */
    public static Gson getGson() {
        return instance.gson;
    }


}
