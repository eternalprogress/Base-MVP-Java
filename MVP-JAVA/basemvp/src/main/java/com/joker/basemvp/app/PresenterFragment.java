package com.joker.basemvp.app;

import android.app.Application;
import android.content.Context;

import com.joker.basemvp.presenter.BaseContract;


/**
 * Created by @ZhangBo
 * on @2017/12/21.
 */

public abstract class PresenterFragment<Presenter extends BaseContract.Presenter> extends BaseFragment implements BaseContract.View<Presenter> {

    protected Presenter mPresenter;

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
}
