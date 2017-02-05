package com.wisdomlanna.www.dagger2_mvp_example.main;

import android.os.Handler;

import com.wisdomlanna.www.dagger2_mvp_example.base.BasePresenter;

import javax.inject.Inject;

class MainPresenter extends BasePresenter<MainView> implements MainInteractor.OnValidatePlusListener {

    private MainInteractor interactor;

    @Inject
    MainPresenter(MainInteractorImpl interactor) {
        super();
        this.interactor = interactor;
    }

    void plus(int x, int y) {
        getView().showProgressDialog();
        interactor.validatePlus(this, x, y);
    }

    void restoreResultPlus(int result) {
        getView().showResultPlus(result);
    }

    @Override
    public void setOnError(String message) {
        getView().hideProgressDialog();
        getView().showError(message);
    }

    @Override
    public void setOnPlusSuccess(int result) {
        new Handler().postDelayed(() -> {
            getView().hideProgressDialog();
            getView().showResultPlus(result);
        }, 1000);
    }
}
