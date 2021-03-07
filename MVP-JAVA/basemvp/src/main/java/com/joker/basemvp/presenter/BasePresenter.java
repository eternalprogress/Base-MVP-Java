package com.joker.basemvp.presenter;

import android.content.Context;

/**
 * Created by @ZhangBo
 * on @2017/12/21.
 */

public class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter {


    private T mView;

    public BasePresenter(T view) {
//       setView(view,context);
       setView(view);
    }

    /**
     * 设置一个View子类可以复写完成
     * @param view view
     */
    @SuppressWarnings("unchecked")
    protected void setView(T view) {
        this.mView = view;
//        mContext = context;
        this.mView.setPresenter(this);
    }

    /**
     * 给子类使用的 获取View的操作
     * 不允许被复写
     * @return mView
     */
    protected final T getView() {
        return mView;
    }
    @Override
    public void start() {

        T view = mView;
        if (view != null) {
            view.showLoading();
        }

    }

    @Override
    public void stop() {

        T view = mView;
        if (view != null) {
            view.dismissLoading();
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public void destroy() {
        T view = mView;
        mView = null;
        if (view != null) {
            //把Presenter设置为null
            view.setPresenter(null);
        }
//        this.mContext = null;
    }
}
