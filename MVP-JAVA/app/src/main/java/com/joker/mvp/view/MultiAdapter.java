package com.joker.mvp.view;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author joker
 * @Date 5/25/22-10:46 AM
 */
public class MultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private List<DataItem> dataSets = new ArrayList<>();
    private SparseArray<DataItem> typeArrays = new SparseArray<>();

    public MultiAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }


    public void addItem(int index, DataItem item, boolean notify) {
        if (index > 0) {
            dataSets.add(index, item);
        } else {
            dataSets.add(item);
        }
        int notifyPos = index > 0 ? index : dataSets.size() - 1;
        if (notify) {
            notifyItemInserted(notifyPos);
        }
    }

    public void addItems(List<DataItem> items, boolean notify) {
        int start = dataSets.size();
        for (DataItem item : items) {
            dataSets.add(item);
        }
        if (notify) {
            notifyItemRangeInserted(start, items.size());
        }

    }

    public DataItem remove(int index) {
        if (index > 0 && index < dataSets.size()) {
            DataItem item = dataSets.remove(index);
            notifyItemRemoved(index);
            return item;
        } else {
            return null;
        }
    }

    public void remove(DataItem item) {
        if (item != null) {
            int index = dataSets.indexOf(item);
            remove(index);
        }
    }


    @Override
    public int getItemViewType(int position) {
        DataItem dataItem = dataSets.get(position);
        int type = dataItem.getClass().hashCode();
        if (typeArrays.indexOfKey(type) < 0) {
            typeArrays.put(type, dataItem);
        }
        return type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataItem dataItem = typeArrays.get(viewType);
        View view = dataItem.getItemView(parent);
        if (view == null) {
            int layoutRes = dataItem.getItemLayoutRes();
            if (layoutRes < 0) {
                throw new RuntimeException("DataItem: " + dataItem.getClass().getName() + "must " +
                        "override getItemView or getItemLayoutRes");
            }
            view = mInflater.inflate(layoutRes, parent, false);
        }
        return createViewHolderInternal(dataItem.getClass(), view);
    }

    private RecyclerView.ViewHolder createViewHolderInternal(Class<? extends DataItem> itemClass,
                                                             View view) {
        Type superclass = itemClass.getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) superclass).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {

                if (RecyclerView.ViewHolder.class.isAssignableFrom(actualTypeArgument.getClass())) {
                    try {
                        return (RecyclerView.ViewHolder) actualTypeArgument.getClass().getConstructor(View.class).newInstance(view);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataItem dataItem = dataSets.get(position);
        dataItem.onBindData(holder, position);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position < dataSets.size()) {
                        DataItem dataItem = dataSets.get(position);
                        int spanSize = dataItem.getSpanSize();
                        return spanSize <= 0 ? spanCount : spanSize;
                    }
                    return spanCount;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataSets.size();
    }

    public void refreshItem(DataItem item) {
        int index = dataSets.indexOf(item);
        notifyItemChanged(index);
    }
}
