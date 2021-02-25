package com.joker.basemvp.net.error;

import android.content.Context;
import android.graphics.Color;


import com.joker.basemvp.net.bean.BaseNetBean;
import com.joker.basemvp.presenter.BaseContract;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * Created by @ZhangBo
 * on @2017/12/25.
 */

public abstract class RxSubscriber<T> implements Observer<T> {

    private BaseContract.View mView;

    public RxSubscriber(BaseContract.View view) {
        mView = view;
    }


    protected abstract void success(T t);
//    protected abstract void netCompleted();

    @Override
    public void onSubscribe(@NonNull Disposable d) { ;
        mView.showLoading();
    }

    @Override
    public void onNext(@NonNull T t) {
            success(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (mView != null) {
            ApiErrorHelper.handleCommonError(e,mView);
            mView.dismissLoading();
        }
    }

    @Override
    public void onComplete() {
//        netCompleted();
        if (mView != null)
            mView.dismissLoading();
    }
}
