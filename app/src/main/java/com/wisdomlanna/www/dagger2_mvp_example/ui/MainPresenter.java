package com.wisdomlanna.www.dagger2_mvp_example.ui;

import com.wisdomlanna.www.dagger2_mvp_example.api.BaseSubscriber;
import com.wisdomlanna.www.dagger2_mvp_example.api.dao.UserInfoDao;
import com.wisdomlanna.www.dagger2_mvp_example.api.service.GitHubApi;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainInterface.View> implements MainInterface.Presenter,
        BaseSubscriber.NetworkCallback {

    private GitHubApi gitHubApi;

    @Inject
    public MainPresenter(GitHubApi gitHubApi) {
        super();
        this.gitHubApi = gitHubApi;
    }

    @Override
    public void plus(int x, int y) {
        getView().showProgressDialog();
        if (getView() != null) {
            try {
                getView().hideProgressDialog();
                getView().showResultPlus(x + y);
            } catch (NumberFormatException e) {
                getView().hideProgressDialog();
                getView().showError(e.getMessage());
            }
        }
    }

    @Override
    public void loadUserInfo(String username) {
        if (getView() != null) {
            getView().showProgressDialog();
            gitHubApi.getUserInfo(username)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseSubscriber<>(this));
        }
    }

    @Override
    public void callX() {
//        githubManager.setX();
//        getView().showX(githubManager.getX());
    }

    @Override
    public <T> void onSuccess(T t) {
        if (getView() != null) {
            getView().hideProgressDialog();
            getView().showResultUserInfoGitHubApi((UserInfoDao) t);
        }
    }

    @Override
    public void onFailure(String message) {
        if (getView() != null) {
            getView().hideProgressDialog();
            getView().showError(message);
        }
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
}