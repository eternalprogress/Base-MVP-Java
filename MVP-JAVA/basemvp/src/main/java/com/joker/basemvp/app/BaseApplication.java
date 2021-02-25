package com.joker.basemvp.app;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

import java.io.File;

/**
 * Created by @ZhangBo
 */

public class BaseApplication extends MultiDexApplication {
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 获取单例
     *
     * @return Application
     */
    public static Application getInstance() {
        return instance;
    }

    /**
     * 获取缓存文件
     *
     * @return 当前的APP的缓存文件夹
     */
    private static File getCacheDirFile() {
        return instance.getCacheDir();
    }

}
