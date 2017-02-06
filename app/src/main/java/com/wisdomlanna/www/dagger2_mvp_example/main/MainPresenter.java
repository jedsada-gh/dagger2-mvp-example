package com.wisdomlanna.www.dagger2_mvp_example.main;

import com.wisdomlanna.www.dagger2_mvp_example.base.BasePresenter;
import com.wisdomlanna.www.dagger2_mvp_example.base.ConnectionCallback;
import com.wisdomlanna.www.dagger2_mvp_example.dao.UserInfoDao;

import javax.inject.Inject;

class MainPresenter extends BasePresenter<MainView> implements MainInteractor.OnValidatePlusListener
        , ConnectionCallback {

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
        getView().hideProgressDialog();
        getView().showError(message);
    }

    @Override
    public void setOnPlusSuccess(int result) {
        getView().hideProgressDialog();
        getView().showResultPlus(result);
    }

    @Override
    public <T> void onSuccess(T t) {
        getView().hideProgressDialog();
        getView().showResultUserInfoGitHubApi((UserInfoDao) t);
    }
}