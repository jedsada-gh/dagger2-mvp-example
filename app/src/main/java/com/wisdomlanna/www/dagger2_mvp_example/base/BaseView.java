package com.wisdomlanna.www.dagger2_mvp_example.base;

public interface BaseView {

    void showProgressDialog();

    void hideProgressDialog();

    void showError(String errorMessage);
}
