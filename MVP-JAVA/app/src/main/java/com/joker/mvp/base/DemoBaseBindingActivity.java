package com.joker.mvp.base;

import android.util.Log;
import android.widget.Toast;

import androidx.viewbinding.ViewBinding;

import com.joker.basemvp.app.RxPresenterActivity;
import com.joker.basemvp.app.RxPresenterBindingActivity;
import com.joker.basemvp.presenter.BaseContract;
import com.joker.basemvp.presenter.BasePresenter;
import com.joker.basemvp.utils.TransformerUtils;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.core.SingleTransformer;

/**
 * @Author joker
 * @Date 2021/2/7-16:39
 */
public abstract class DemoBaseBindingActivity<VB extends ViewBinding,Presenter extends BaseContract.Presenter> extends RxPresenterBindingActivity<VB,Presenter> {


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
