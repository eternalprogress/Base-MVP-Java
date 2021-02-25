package com.joker.basemvp.net.bean;

import android.text.TextUtils;

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

    public boolean success() {
        return TextUtils.equals(status,SUCCEED);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}