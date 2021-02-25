package com.joker.mvp.net;

import com.joker.basemvp.net.bean.BaseModel;
import com.joker.mvp.bean.LoginBean;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @Author joker
 * @Date 2021/2/7-15:18
 */
public interface Service {
    /**
     * 获取随机数的接口
     *
     * @return String 后台生成的随机数
     */
    @GET("login/{name}/{pwd}")
    Observable<LoginBean> login(@Path("name") String name, @Path("pwd")String pwd);
}
