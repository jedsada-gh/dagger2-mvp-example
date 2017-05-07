package com.wisdomlanna.www.dagger2_mvp_example.template.frangment;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BasePresenter;

import javax.inject.Inject;

class CustomFragmentPresenter extends BasePresenter<CustomFragmentInterface.View>
        implements CustomFragmentInterface.Presenter {

    @Inject
    CustomFragmentPresenter() {
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
