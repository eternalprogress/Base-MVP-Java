package com.joker.basemvp.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Created by @ZhangBo
 *
 */

public abstract class BaseBindingFragment<VB extends ViewBinding> extends Fragment {
    protected View mRoot;
    private ProgressDialog loadingDialog;
    protected VB mBinding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //初始化参数
        initArgs(getArguments());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        if (mRoot == null) {
           useViewBinding(inflater,container);
            mRoot = mBinding.getRoot();
            initWidget(mRoot);
        } else {
            if (mRoot.getParent() != null) {
                //把当前Root从其父控件中移除
                ((ViewGroup)mRoot.getParent()).removeView(mRoot);
            }
        }

        return mRoot;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void useViewBinding(LayoutInflater inflater, ViewGroup container) {
        Type superclass = getClass().getGenericSuperclass();
        Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
        try {
            Method method = aClass.getDeclaredMethod("inflate", LayoutInflater.class,ViewGroup.class,Boolean.class);
            mBinding = (VB) method.invoke(null, inflater,container,false);
        } catch (NoSuchMethodException | IllegalAccessException| InvocationTargetException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //当View创建完成之后初始化数据
        initData();
    }
    /**
     * 初始化相关参数
     * @param bundle  参数Bundle
     *
     */
    protected void initArgs(Bundle bundle) {

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
     * @param root
     */
    protected void initWidget(View root) {
        if (needEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }
    /**
     * 返回按钮触发时
     * @return  返回true代表我已经处理返回逻辑,Activity不用自己finish.
     * 返回false代表我没有处理,activity自己走自己的逻辑
     */
     public boolean onBackPressed() {
         return false;
     }


    @Override
    public void onDestroy() {
        mBinding = null;
        super.onDestroy();

        if (needEventBus()) {
            EventBus.getDefault().unregister(this);
        }


    }
}
