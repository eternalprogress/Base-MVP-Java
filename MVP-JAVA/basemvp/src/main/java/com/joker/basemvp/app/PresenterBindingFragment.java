package com.joker.basemvp.app;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.viewbinding.ViewBinding;

import com.joker.basemvp.presenter.BaseContract;


/**
 * Created by @ZhangBo
 * on @2017/12/21.
 */

public abstract class PresenterBindingFragment<VB extends ViewBinding,Presenter extends BaseContract.Presenter> extends BaseBindingFragment<VB> implements BaseContract.View<Presenter> {

    protected Presenter mPresenter;

    /**
     * 初始化Presenter
     * @return  Presenter
     */
    protected abstract Presenter initPresenter(Context context);

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
