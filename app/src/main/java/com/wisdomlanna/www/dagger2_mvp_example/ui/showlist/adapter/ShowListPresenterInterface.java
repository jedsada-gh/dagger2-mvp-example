package com.wisdomlanna.www.dagger2_mvp_example.ui.showlist.adapter;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter.BaseListAdapterInterface;

class ShowListPresenterInterface {

    interface Adapter extends BaseListAdapterInterface.Adapter {

    }

    interface Presenter extends BaseListAdapterInterface.Presenter<ShowListPresenterInterface.Adapter> {

    }
}
