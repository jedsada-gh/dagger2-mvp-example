package com.wisdomlanna.www.dagger2_mvp_example.base;

public interface BaseView {

    interface ErrorCallback {
        void onServerError(String message);

        void onGenericError(String message);

        void onUnAuthorized();
    }

    void showProgressDialog();

    void hideProgressDialog();

    void showError(String errorMessage);

    void unAuthorizedApi();
}