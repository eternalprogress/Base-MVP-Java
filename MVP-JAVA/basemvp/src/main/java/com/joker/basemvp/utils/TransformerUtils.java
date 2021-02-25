package com.joker.basemvp.utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Author joker
 * @Date 2021/2/25-19:23
 */
public class TransformerUtils {
    public static <Upstream> ObservableTransformer<@NonNull Upstream, @NonNull Upstream> RxIOForMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static <Upstream> SingleTransformer<@NonNull Upstream, @NonNull Upstream> ioForMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
