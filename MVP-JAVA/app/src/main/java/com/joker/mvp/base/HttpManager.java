package com.joker.mvp.base;

import com.joker.basemvp.net.HttpUtils;
import com.joker.mvp.net.Service;
import com.joker.mvp.utils.Common;

/**
 * @Author joker
 * @Date 2021/2/7-15:16
 */
public class HttpManager {
    private HttpManager() {}
    private static class MyHttpManagerHolder {
        private static final HttpManager INSTANCE = new HttpManager();
    }

    public static HttpManager getInstance() {
        return HttpManager.MyHttpManagerHolder.INSTANCE;
    }
    /**
     * 运行在子线程
     * @return Service
     */
    public Service ServiceIO() {
        return HttpUtils.getInstance().initRetrofitIO(Common.Constance.API_URL,BaseModel.class).create(Service.class);
    }
    /**
     * 运行在默认线程
     * @return Service
     */
    public Service Service() {
        return HttpUtils.getInstance().initRetrofit(Common.Constance.API_URL,BaseModel.class).create(Service.class);
    }

}
