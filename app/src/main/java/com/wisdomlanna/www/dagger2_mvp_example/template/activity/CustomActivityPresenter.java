package com.wisdomlanna.www.dagger2_mvp_example.template.activity;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BasePresenter;

import javax.inject.Inject;

class CustomActivityPresenter extends BasePresenter<CustomActivityInterface.View> implements CustomActivityInterface.Presenter {

    @Inject
    CustomActivityPresenter() {
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
