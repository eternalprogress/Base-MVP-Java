package com.joker.mvp.base;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.joker.basemvp.app.RxPresenterActivity;
import com.joker.basemvp.presenter.BaseContract;
import com.joker.basemvp.utils.TransformerUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
    public  <Upstream> ObservableTransformer<@NonNull Upstream, @NonNull Upstream> RxIOForMain() {
        return TransformerUtils.RxIOForMain();
    }
    public  <Upstream> SingleTransformer<@NonNull Upstream, @NonNull Upstream> ioForMain() {
        return TransformerUtils.ioForMain();
    }
}
