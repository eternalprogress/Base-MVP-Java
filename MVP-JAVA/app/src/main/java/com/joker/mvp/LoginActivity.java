package com.joker.mvp;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joker.mvp.base.DemoBaseActivity;
import com.joker.mvp.presenter.LoginContract;
import com.joker.mvp.presenter.LoginPresenter;
import com.joker.mvp.view.DataItem;
import com.joker.mvp.view.MultiAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends DemoBaseActivity<LoginContract.Presenter> implements LoginContract.View {


    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.re)
    RecyclerView recyclerView;
    List<Object> data = new ArrayList<>();
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();


        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        MultiAdapter adapter = new MultiAdapter(this);
        recyclerView.setAdapter(adapter);
        List<DataItem> items = new ArrayList<>();
        items.add(new TopBanner(data));
        items.add(new MidItem(data));
        adapter.addItems(items,false);


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
