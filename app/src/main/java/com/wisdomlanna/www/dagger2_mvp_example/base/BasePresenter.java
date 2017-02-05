package com.wisdomlanna.www.dagger2_mvp_example.base;

import android.content.res.Resources;

import javax.inject.Inject;

public abstract class BasePresenter<V extends BaseView> {

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
}