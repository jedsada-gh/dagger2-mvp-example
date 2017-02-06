package com.wisdomlanna.www.dagger2_mvp_example.main;

import com.wisdomlanna.www.dagger2_mvp_example.base.BasePresenter;
import com.wisdomlanna.www.dagger2_mvp_example.dao.UserInfoDao;

import javax.inject.Inject;

class MainPresenter extends BasePresenter<MainView> implements MainInteractor.OnValidatePlusListener
        , MainInteractor.OnUserInfoGitHubListener {

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
        if (getView() != null) {
            getView().showProgressDialog();
            interactor.loadUserInfoGitHub(userName, this);
        }
    }

    @Override
    public void setOnError(String message) {
        getView().showError(message);
    }

    @Override
    public void setOnPlusSuccess(int result) {
        getView().showResultPlus(result);
    }

    @Override
    public void onSuccess(UserInfoDao dao) {
        if (getView() != null) {
            getView().hideProgressDialog();
            getView().showResultUserInfoGitHubApi(dao);
        }
    }

    @Override
    public void onError(String message) {
        if (getView() != null) {
            getView().hideProgressDialog();
            getView().showError(message);
        }
    }

    @Override
    public void onFailure(String message) {
        if (getView() != null) {
            getView().hideProgressDialog();
            getView().showError(message);
        }
    }
}