package com.joker.mvp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joker.mvp.base.DemoBaseActivity;
import com.joker.mvp.base.DemoBaseBindingActivity;
import com.joker.mvp.databinding.ActivityMainBinding;
import com.joker.mvp.presenter.LoginContract;
import com.joker.mvp.presenter.LoginPresenter;
import com.joker.mvp.view.DataItem;
import com.joker.mvp.view.MultiAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginVBActivity extends DemoBaseBindingActivity<ActivityMainBinding,LoginContract.Presenter> implements LoginContract.View, View.OnClickListener {



    @Override
    protected LoginContract.Presenter initPresenter(Context context) {
        return new LoginPresenter(this);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
//        recyclerView =mBinding.re;
//        etName = mBinding.etName;
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        MultiAdapter adapter = new MultiAdapter(this);
//        recyclerView.setAdapter(adapter);
//        List<DataItem> items = new ArrayList<>();
//        items.add(new TopBanner(data));
//        items.add(new MidItem(data));
//        adapter.addItems(items,false);
        mBinding.etName.setText("dadadada1111111");
        mBinding.btLogin.setOnClickListener(this);

    }


    @Override
    public void loginSuccess(String name) {
        Log.e("============","登录成功了" + name);
        Toast.makeText(this, name + "登录成功了", Toast.LENGTH_SHORT).show();
        }



    @Override
    public void onClick(View view) {
        mPresenter.login(mBinding.etName.getText().toString(),"dadadadada");
    }
}
