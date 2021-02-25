package com.joker.basemvp.app;

import android.content.Context;

import androidx.lifecycle.Lifecycle;

import com.joker.basemvp.presenter.BaseContract;
import com.trello.lifecycle4.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle4.LifecycleProvider;
import com.trello.rxlifecycle4.LifecycleTransformer;


/**
 * Created by @ZhangBo
 * on @2017/12/21.
 */

public abstract class RxPresenterFragment<Presenter extends BaseContract.Presenter> extends BaseFragment implements BaseContract.View<Presenter> {

    protected Presenter mPresenter;
    private final LifecycleProvider<Lifecycle.Event> provider
            = AndroidLifecycle.createLifecycleProvider(this);
    /**
     * 初始化Presenter
     * @return  Presenter
     */
    protected abstract Presenter initPresenter(Context context);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //在界面onAttach之后触发Presenter
        initPresenter(context);
    }


    @Override
    public void setPresenter(Presenter presenter) {
        //View中赋值Presenter
        mPresenter = presenter;
    }

    @Override
    public <M> LifecycleTransformer<M> bindUntilEvent() {
        return provider.bindUntilEvent(Lifecycle.Event.ON_DESTROY);
    }
}
