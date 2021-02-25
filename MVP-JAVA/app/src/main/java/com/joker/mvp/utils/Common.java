package com.joker.mvp.utils;

/**
 * @Author joker
 * @Date 2021/2/7-15:23
 */
public class Common {
    /**
     * 一些不可变的永恒的参数
     */
    public interface Constance{
        //手机号的正则,11位手机号
        String REGEX_MOBILE = "[1][3,4,5,7,8][0-9]{9}$";
        String API_URL = "https://www.fastmock.site/mock/4dadfc73c3e20267223eebca11d3632b/study/";
    }
}
