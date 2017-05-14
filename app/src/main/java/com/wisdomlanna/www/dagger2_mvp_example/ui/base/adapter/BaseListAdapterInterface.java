package com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter;

import java.util.List;

public interface BaseListAdapterInterface {

    interface Adapter {
        Presenter getPresenter();

        void notifyDataSetChanged();

        void notifyItemInserted(int index);

        void notifyItemRemoved(int index);
    }

    interface Presenter<A extends BaseListAdapterInterface.Adapter> {
        void setAdapter(A adapter);

        A getAdapter();

        int getItemViewType(int pos);

        int getItemCount();

        boolean hasItems();

        List<BaseItem> getItems();

        BaseItem getItem(int pos);

        void setItems(List<BaseItem> items);

        void addItem(BaseItem item);

        void removeItem(int index);

        void removeAllItems();
    }
}
