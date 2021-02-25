package com.joker.mvp.base;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.joker.basemvp.app.RxPresenterActivity;
import com.joker.basemvp.presenter.BaseContract;

/**
 * @Author joker
 * @Date 2021/2/7-16:39
 */
public abstract class DemoBaseActivity<Presenter extends BaseContract.Presenter > extends RxPresenterActivity<Presenter> {


    @Override
    public void showError(String status, String msg) {
        Log.e("============","showError" + msg);
        Toast.makeText(this, "status: " + status + "msg: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        Log.e("============","showLoading");
        Toast.makeText(this, "开始dialog", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        Log.e("============","dismissLoading");
        Toast.makeText(this, "结束dialog", Toast.LENGTH_SHORT).show();
    }
}
