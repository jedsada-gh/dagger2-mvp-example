package com.wisdomlanna.www.dagger2_mvp_example.ui.frangment;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BasePresenter;

import javax.inject.Inject;

class MainFragmentPresenter extends BasePresenter<MainFragmentInterface.View>
        implements MainFragmentInterface.Presenter {

    @Inject
    MainFragmentPresenter() {
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
    public void testFragment() {
        getView().testResult();
    }
}
