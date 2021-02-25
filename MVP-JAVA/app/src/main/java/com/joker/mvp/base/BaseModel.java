package com.joker.mvp.base;

import android.text.TextUtils;

import com.joker.basemvp.net.bean.BaseNetBean;

/**
 * @author zhangbo
 * @version 1.0.0
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class BaseModel<T> implements BaseNetBean<T>{
    public static final String SUCCEED = "200";


    private String status;
    private String msg;
    private T data;


    @Override
    public boolean isSuccess() {
        return TextUtils.equals(status,SUCCEED);
    }
    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public T getData() {
        return data;
    }


}