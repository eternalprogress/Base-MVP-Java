package com.joker.basemvp.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by @ZhangBo
 *
 */

public abstract class BaseFragment extends Fragment {
    protected View mRoot;
    protected Unbinder mRootUnBinder;
    private ProgressDialog loadingDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //初始化参数
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        if (mRoot == null) {
            int layId = getContentLayoutId();
            //初始化当前的根布局,但是不在创建时就添加到container里面去
            View root = inflater.inflate(layId,container,false);
            mRoot = root;
            initWidget(root);
        } else {
            if (mRoot.getParent() != null) {
                //把当前Root从其父控件中移除
                ((ViewGroup)mRoot.getParent()).removeView(mRoot);
            }
        }

        return mRoot;

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
     * 得到去当前的资源文件
     * @return 资源文件Id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     * @param root
     */
    protected void initWidget(View root) {
       mRootUnBinder= ButterKnife.bind(this,root);
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
        super.onDestroy();

        if (mRootUnBinder!=null) {
            mRootUnBinder.unbind();
        }

        if (needEventBus()) {
            EventBus.getDefault().unregister(this);
        }


    }
}
