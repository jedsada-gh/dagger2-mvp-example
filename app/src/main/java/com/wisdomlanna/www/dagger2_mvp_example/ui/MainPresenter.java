package com.wisdomlanna.www.dagger2_mvp_example.ui;

import com.wisdomlanna.www.dagger2_mvp_example.R;
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
    MainPresenter(GitHubApi gitHubApi) {
        super();
        this.gitHubApi = gitHubApi;
    }

    @Override
    public void plus(String x, String y) {
        getView().showProgressDialog();
        if (getView() != null) {
            try {
                getView().hideProgressDialog();
                getView().showResultPlus(Integer.parseInt(x) + Integer.parseInt(y));
            } catch (NumberFormatException e) {
                getView().hideProgressDialog();
                getView().showError(R.string.invalid_number_format);
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
        if(getView() != null) getView().showMessage(R.string.view_create);
    }

    @Override
    public void onViewDestroy() {
        if(getView() != null) getView().showMessage(R.string.view_destroy);
    }

    @Override
    public void onViewStart() {
        if(getView() != null) getView().showMessage(R.string.view_start);
    }

    @Override
    public void onViewStop() {
        if(getView() != null) getView().showMessage(R.string.view_stop);
    }
}