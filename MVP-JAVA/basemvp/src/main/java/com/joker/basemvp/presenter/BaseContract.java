package com.joker.basemvp.presenter;

import com.trello.rxlifecycle4.LifecycleTransformer;

/**
 *MVP模式中公共的基本契约
 *
 * Created by @ZhangBo
 * on @2017/12/21.
 */

public interface BaseContract {

    interface View<T extends Presenter> {

        //显示一个字符串错误
        void showError(String status,String msg);

        //显示一个进度条
        void showLoading();
        //取消一个进度条
        void dismissLoading();

        //支持设置一个Presenter
        void setPresenter(T presenter);

       <M> LifecycleTransformer<M> bindUntilEvent();
    }


    interface Presenter{
        //公用的开始方法
        void start();

        //公用的销毁触发
        void destroy();

    }


}
