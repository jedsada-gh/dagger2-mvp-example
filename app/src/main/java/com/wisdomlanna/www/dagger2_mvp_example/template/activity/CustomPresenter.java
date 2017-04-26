package com.wisdomlanna.www.dagger2_mvp_example.template.activity;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BasePresenter;

import javax.inject.Inject;

class CustomPresenter extends BasePresenter<CustomInterface.View> implements CustomInterface.Presenter {

    @Inject
    CustomPresenter() {
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
