package com.joker.mvp.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author joker
 * @Date 5/25/22-10:28 AM
 */
public abstract class DataItem<DATA, VH extends RecyclerView.ViewHolder> {

    private DATA data;
    private MultiAdapter adapter;

    public DataItem(DATA data) {
        this.data = data;
    }

    public abstract void onBindData(RecyclerView.ViewHolder holder, int position);

    /**
     * 返回该item的布局资源id
     *
     * @return 布局资源id
     */
    public int getItemLayoutRes() {
        return -1;
    }

    /**
     * 返回该item的视图View
     *
     * @param viewGroup ViewGroup
     * @return 视图View
     */
    public View getItemView(ViewGroup viewGroup) {
        return null;
    }

    public void setAdapter(MultiAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * 刷新列表
     */
    void refreshItem() {
        if (adapter != null) {
            adapter.refreshItem(this);
        }
    }

    /**
     * 从列表中移除
     */
    void removeItem() {
        if (adapter != null) {
            adapter.remove(this);
        }

    }

    /**
     * 控制item占据几列
     *
     * @return
     */
    public int getSpanSize() {
        return 0;
    }

    ;
}
