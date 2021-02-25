package com.joker.mvp.base;

import android.widget.Toast;

import com.joker.basemvp.app.BaseFragment;
import com.joker.basemvp.app.RxPresenterFragment;
import com.joker.basemvp.presenter.BaseContract;

/**
 * @Author joker
 * @Date 2021/2/7-16:49
 */
public abstract class DemoBaseFragment<Presenter extends BaseContract.Presenter > extends RxPresenterFragment<Presenter> {
    @Override
    public void showError(String status, String msg) {
        Toast.makeText(getActivity(), "status: " + status + "msg: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        Toast.makeText(getActivity(), "开始dialog", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        Toast.makeText(getActivity(), "结束dialog", Toast.LENGTH_SHORT).show();
    }
}
