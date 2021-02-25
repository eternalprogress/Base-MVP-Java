package com.joker.basemvp.net.bean;

/**
 * @Author joker
 * @Date 2021/2/25-13:50
 */
public interface BaseNetBean<T> {
    boolean isSuccess();
    String getStatus();
    String getMsg();
    T getData();
}
