package com.joker.mvp.view;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author joker
 * @Date 5/25/22-2:34 PM
 */
public class MultiViewHolder extends RecyclerView.ViewHolder {
    public MultiViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    private SparseArray<View> viewCache = new SparseArray<>();

    public <T extends View > T findViewById(int viewId) {
        View view = viewCache.get(viewId);
        if (view == null) {
            view= itemView.findViewById(viewId);
            viewCache.put(viewId,view);
        }
        return (T) view;
    }
}
