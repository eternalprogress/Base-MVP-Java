package com.joker.basemvp.net.error;

import android.content.Context;


import com.joker.basemvp.presenter.BaseContract;

import java.io.IOException;

import retrofit2.HttpException;


/**
 * Created by @ZhangBo
 * on @2017/12/2.
 */

//辅助处理异常
public class ApiErrorHelper {

    public static void handleCommonError(Throwable e, BaseContract.View view) {
        if (e instanceof HttpException) {
            decodeRspCode(String.valueOf(((HttpException) e).code()), "服务器错误", view);
        } else if (e instanceof IOException) {
            decodeRspCode("0xfffff", "连接失败", view);
        } else if (e instanceof ApiException) {
            //ApiException处理
            ApiException exception = (ApiException) e;
            String code = exception.getErrorCode();

            decodeRspCode(code,exception.getMessage(), view);
        } else {
            decodeRspCode("0xfffff1", e.getMessage(), view);
        }


    }




    private static void decodeRspCode(String code, final String errMsg,
                                      final BaseContract.View view) {
        if (view != null)
            view.showError(code,errMsg);
    }



}
