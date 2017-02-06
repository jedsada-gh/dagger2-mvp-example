package com.wisdomlanna.www.dagger2_mvp_example.main;

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

    void loadUserInfoGitHub(String userName) {
        getView().showProgressDialog();
        interactor.loadUserInfoGitHub(userName);
    }

    @Override
    public void setOnError(String message) {
        getView().showError(message);
    }

    @Override
    public void setOnPlusSuccess(int result) {
        getView().showResultPlus(result);
    }
}
