package com.joker.basemvp.app;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.joker.basemvp.utils.ScreenUtils;
import com.joker.basemvp.utils.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by @ZhangBo
 *
 */

public abstract class BaseBindingActivity<VB extends ViewBinding> extends AppCompatActivity {

    protected VB mBinding;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在界面未初始化之前调用的初始化窗口
        initWindows();
        if (initArgs(getIntent().getExtras())) {
            //得到界面id并设置上去
            useViewBinding();
            initWidget();
            initData();
            if (needEventBus()) {
                EventBus.getDefault().register(this);
            }
        }else {
            finish();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void useViewBinding() {
        Type superclass = getClass().getGenericSuperclass();
        Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
        try {
            Method method = aClass.getDeclaredMethod("inflate", LayoutInflater.class);
            mBinding = (VB) method.invoke(null, getLayoutInflater());
            setContentView(mBinding.getRoot());
        } catch (NoSuchMethodException | IllegalAccessException| InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始化窗口
     */
    protected void initWindows(){
        StatusBarUtil.immersive(getWindow());
    }

    /**
     * 初始化相关参数
     * @param bundle  参数Bundle
     * @return 如果参数正确返回true,错误返回false
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }
    /**
     * 是否需要EventBus
     * @return 如果需要返回true,不需要返回false 默认不需要
     */
    protected boolean needEventBus() {
        return false;
    }


    /**
     * 初始化控件
     */
    protected void initWidget() {

    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }


    @Override
    public boolean onSupportNavigateUp() {
        //当点击界面导航返回时,Finish当前界面
        finish();
        return super.onSupportNavigateUp();
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (ScreenUtils.isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
    

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (needEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }
}
