package com.wisdomlanna.www.dagger2_mvp_example.ui.showlist;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BasePresenter;

import javax.inject.Inject;

class ShowListActivityPresenter extends BasePresenter<ShowListInterface.View> implements ShowListInterface.Presenter {

    @Inject
    ShowListActivityPresenter() {
        super();
    }

    @Override
    public void onViewCreate() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void onViewStart() {

    }

    @Override
    public void onViewStop() {

    }

    @Override
    public void test() {
        getView().testResult();
    }
}
