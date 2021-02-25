package com.joker.mvp.presenter;

import com.joker.basemvp.presenter.BaseContract;

/**
 * @Author joker
 * @Date 2021/2/7-17:01
 */
public interface LoginContract {
    interface Presenter extends BaseContract.Presenter {
        void login(String name,String pwd);
    }
    interface View extends BaseContract.View<Presenter> {
        void loginSuccess(String name);
    }
}
