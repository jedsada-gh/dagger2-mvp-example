package com.wisdomlanna.www.dagger2_mvp_example.ui.showlist.adapter;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter.BaseListAdapterPresenter;

class ShowListAdapterPresenter extends BaseListAdapterPresenter<ShowListPresenterInterface.Adapter>
        implements ShowListPresenterInterface.Presenter {

    public static ShowListPresenterInterface.Presenter create() {
        return new ShowListAdapterPresenter();
    }
}
