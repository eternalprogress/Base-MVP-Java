package com.joker.basemvp.app;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.Lifecycle;

import com.joker.basemvp.presenter.BaseContract;
import com.trello.lifecycle4.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle4.LifecycleProvider;
import com.trello.rxlifecycle4.LifecycleTransformer;


/**
 * Created by Joker on 2018/4/10.
 */

public abstract class RxPresenterActivity<Presenter extends BaseContract.Presenter> extends BaseActivity implements BaseContract.View<Presenter>{
    protected Presenter mPresenter;
    public final LifecycleProvider<Lifecycle.Event> provider
            = AndroidLifecycle.createLifecycleProvider(this);
    /**
     * 初始化Presenter
     * @return  Presenter
     */
    protected abstract Presenter initPresenter(Context context);

    @Override
    protected void initWidget() {
        super.initWidget();
        //在界面onAttach之后触发Presenter
        initPresenter(this);


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }
}
