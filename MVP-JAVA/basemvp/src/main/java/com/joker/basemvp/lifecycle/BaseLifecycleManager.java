package com.joker.basemvp.lifecycle;

import android.app.Activity;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;


import com.joker.basemvp.utils.AppManager;
import com.joker.basemvp.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author: Joker
 * @Date: 2019/7/24 14:31
 * @Description:
 */
public class BaseLifecycleManager {
    public static void bindButterKnife(LifecycleOwner lifecycleOwner, Activity activity) {
        new BaseLifecycle(lifecycleOwner, activity);
    }

    static class BaseLifecycle implements LifecycleObserver {
        private Activity activity;

        private BaseLifecycle(LifecycleOwner lifecycleOwner, Activity activity) {
            this.activity = activity;
            lifecycleOwner.getLifecycle().addObserver(this);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        void bindButterKnife() {
            AppManager.getAppManager().addActivity(activity);
            StatusBarUtil.immersive(activity);

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void unBindButterKnife() {
            AppManager.getAppManager().removeActivity(activity);
        }
    }

}
