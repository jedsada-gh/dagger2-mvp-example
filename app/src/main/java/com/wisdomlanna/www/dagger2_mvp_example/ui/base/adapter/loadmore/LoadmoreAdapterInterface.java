package com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter.loadmore;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter.BaseItem;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter.BaseListAdapterInterface;

import java.util.List;

public interface LoadmoreAdapterInterface {

    interface Adapter extends BaseListAdapterInterface.Adapter {
    }

    interface Presenter<A extends LoadmoreAdapterInterface.Adapter>
            extends BaseListAdapterInterface.Presenter<A> {
        void setItems(List<BaseItem> items, boolean isNextItemAvailable);
    }
}

