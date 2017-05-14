package com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter.loadmore;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter.BaseItem;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter.BaseItemType;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter.BaseListAdapterPresenter;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter.progress.ProgressItem;

import java.util.List;

public abstract class LoadmoreAdapterPresenter<A extends LoadmoreAdapterInterface.Adapter>
        extends BaseListAdapterPresenter<A> implements LoadmoreAdapterInterface.Presenter<A> {

    private boolean isNextItemAvailable;

    @Override
    public int getItemViewType(int pos) {
        if (pos >= super.getItemCount()) {
            return BaseItemType.TYPE_PROGRESS;
        }
        return super.getItemViewType(pos);
    }

    @Override
    public int getItemCount() {
        int count = super.getItemCount();
        if (isNextItemAvailable) {
            count++;
        }
        return count;
    }


    @Override
    public BaseItem getItem(int pos) {
        if (pos >= super.getItemCount()) {
            return new ProgressItem();
        }
        return super.getItem(pos);
    }

    @Override
    public void setItems(List<BaseItem> items, boolean isNextItemAvailable) {
        super.setItems(items);
        this.isNextItemAvailable = isNextItemAvailable;
    }
}
