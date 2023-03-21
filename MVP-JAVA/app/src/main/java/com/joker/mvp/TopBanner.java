package com.joker.mvp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.joker.mvp.view.DataItem;
import com.joker.mvp.view.MultiViewHolder;

import java.util.List;

/**
 * @Author joker
 * @Date 5/25/22-2:49 PM
 */
public class TopBanner extends DataItem<List<Object>, MultiViewHolder> {
    public TopBanner(List<Object> objects) {
        super(objects);
    }

    @Override
    public void onBindData(RecyclerView.ViewHolder holder, int position) {
        ((TextView)holder.itemView).setText("哈哈昂");
    }

    @Override
    public View getItemView(ViewGroup viewGroup) {

        return new TextView(viewGroup.getContext());
    }
}
