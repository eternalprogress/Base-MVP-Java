package com.joker.mvp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.joker.basemvp.net.bean.BaseModel;
import com.joker.basemvp.net.error.RxSubscriber;
import com.joker.mvp.base.DemoBaseActivity;
import com.joker.mvp.base.HttpManager;
import com.joker.mvp.bean.LoginBean;
import com.joker.mvp.presenter.LoginContract;
import com.joker.mvp.presenter.LoginPresenter;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends DemoBaseActivity<LoginContract.Presenter> implements LoginContract.View {


    @BindView(R.id.et_name)
    EditText etName;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected LoginContract.Presenter initPresenter(Context context) {
        return new LoginPresenter(this);
    }

    @Override
    public void loginSuccess(String name) {
        Log.e("============","登录成功了" + name);
        Toast.makeText(this, name + "登录成功了", Toast.LENGTH_SHORT).show();
        }


    @OnClick(R.id.bt_login)
    public void onViewClicked() {
        mPresenter.login(etName.getText().toString(),"dadadadada");

    }
}
