package com.joker.basemvp.net.error;

import android.content.Context;



import com.joker.basemvp.presenter.BaseContract;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;


/**
 * Created by @ZhangBo
 * on @2017/12/25.
 */

public abstract class RxNDSubscriber<T> implements Observer<T> {

    private BaseContract.View mView;

    public RxNDSubscriber( BaseContract.View view) {
        mView = view;
    }


    protected abstract void success(T t);


    @Override
    public void onNext(@NonNull T t) {
        success(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (mView != null)
            ApiErrorHelper.handleCommonError( e,mView);
    }

}
