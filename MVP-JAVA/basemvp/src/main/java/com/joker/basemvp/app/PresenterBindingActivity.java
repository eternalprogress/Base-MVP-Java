package com.joker.basemvp.app;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.viewbinding.ViewBinding;

import com.joker.basemvp.presenter.BaseContract;


/**
 * Created by Joker on 2018/4/10.
 */

public abstract class PresenterBindingActivity<VB extends ViewBinding,Presenter extends BaseContract.Presenter> extends BaseBindingActivity<VB> implements BaseContract.View<Presenter>{
    protected Presenter mPresenter;

//    /**
//     * 初始化Presenter
//     * @return  Presenter
//     */
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    protected  Presenter initPresenter(Context context) {
//
//            Type superclass = getClass().getGenericSuperclass();
//            Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[1];
//
//        try {
//            Constructor cstr = aClass.getDeclaredConstructor(Context.class);
//            cstr.setAccessible(true);
//            mPresenter = (Presenter) cstr.newInstance(context);
//            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
//                e.printStackTrace();
//            }
//        return mPresenter;
//
//        }
    /**
     * 初始化Presenter
     * @return  Presenter
     */
    protected abstract Presenter initPresenter(Context context);

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initWidget() {
        super.initWidget();
        //在界面onAttach之后触发Presenter
        initPresenter(this);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        //View中赋值Presenter
        mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }
}
