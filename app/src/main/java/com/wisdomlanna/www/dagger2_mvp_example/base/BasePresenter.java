package com.wisdomlanna.www.dagger2_mvp_example.base;

import android.content.res.Resources;

import javax.inject.Inject;

public abstract class BasePresenter<V extends BaseView> implements BaseView.ErrorCallback {

    private V view;

    @Inject
    Resources resources;

    void attachView(V view) {
        this.view = view;
    }

    void detachView() {
        view = null;
    }

    protected V getView() {
        return view;
    }

    protected Resources getResources() {
        return resources;
    }

    @Override
    public void onServerError(String message) {
        view.hideProgressDialog();
        view.showError(message);
    }

    @Override
    public void onGenericError(String message) {
        view.hideProgressDialog();
        view.showError(message);
    }

    @Override
    public void onUnAuthorized() {
        view.hideProgressDialog();
        view.unAuthorizedApi();
    }
}