package com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.exception.MvpViewNotAttachedException;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapterPresenter<A extends BaseListAdapterInterface.Adapter>
        implements BaseListAdapterInterface.Presenter<A> {

    private WeakReference<A> adapter;
    private List<BaseItem> items;

    public BaseListAdapterPresenter() {
        this.items = new ArrayList<>();
    }

    @Override
    public void setAdapter(A adapter) {
        this.adapter = new WeakReference<>(adapter);
    }

    @Override
    public A getAdapter() {
        if (adapter != null) return adapter.get();
        throw new MvpViewNotAttachedException();
    }

    @Override
    public int getItemViewType(int pos) {
        return getPrivateItems().get(pos).getType();
    }

    @Override
    public int getItemCount() {
        return getPrivateItems().size();
    }

    @Override
    public List<BaseItem> getItems() {
        return getPrivateItems();
    }

    @Override
    public BaseItem getItem(int pos) {
        return getPrivateItems().get(pos);
    }

    @Override
    public boolean hasItems() {
        return getItemCount() > 0;
    }

    @Override
    public void setItems(List<BaseItem> items) {
        this.items = items;
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public void addItem(BaseItem item) {
        getPrivateItems().add(item);
        getAdapter().notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public void removeItem(int index) {
        getPrivateItems().remove(index);
        getAdapter().notifyItemRemoved(index);
    }

    @Override
    public void removeAllItems() {
        getPrivateItems().clear();
        getAdapter().notifyDataSetChanged();
    }

    private List<BaseItem> getPrivateItems() {
        if (items == null) return new ArrayList<>();
        return items;
    }
}
