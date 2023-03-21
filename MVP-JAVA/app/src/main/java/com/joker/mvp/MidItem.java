package com.joker.mvp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.joker.mvp.view.DataItem;
import com.joker.mvp.view.MultiViewHolder;

import java.util.List;

/**
 * @Author joker
 * @Date 5/25/22-2:49 PM
 */
public class MidItem extends DataItem<List<Object>, MultiViewHolder> {
    public MidItem(List<Object> objects) {
        super(objects);
    }

    @Override
    public void onBindData(RecyclerView.ViewHolder holder, int position) {
        EditText et = holder.itemView.findViewById(R.id.et_name);
        et.setText("大大大");
    }

    @Override
    public int getItemLayoutRes() {
        return R.layout.activity_main;
    }
}
