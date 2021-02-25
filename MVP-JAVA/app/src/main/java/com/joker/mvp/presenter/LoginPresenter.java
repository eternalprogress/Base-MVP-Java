package com.joker.mvp.presenter;


import com.joker.basemvp.net.error.RxSubscriber;
import com.joker.basemvp.presenter.BasePresenter;
import com.joker.mvp.base.HttpManager;
import com.joker.mvp.bean.LoginBean;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;


/**
 * @Author joker
 * @Date 2021/2/7-17:03
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String name, String pwd) {
        HttpManager.getInstance().ServiceIO().login(name,pwd)
                .compose(getView().bindUntilEvent())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<LoginBean>(getView()) {
                    @Override
                    protected void success(LoginBean loginBean) {
                        getView().loginSuccess(loginBean.getName());
                    }
                });

    }
}
