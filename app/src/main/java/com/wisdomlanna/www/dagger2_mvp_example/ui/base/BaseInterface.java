package com.wisdomlanna.www.dagger2_mvp_example.ui.base;

public class BaseInterface {

    public interface NetworkErrorCallback {
        void onGenericError(String message);

        void onUnAuthorized();
    }

    public interface View {
        void showProgressDialog();

        void hideProgressDialog();

        void showError(String errorMessage);

        void unAuthorizedApi();
    }

    interface Presenter<V extends BaseInterface.View> {

        void attachView(V view);

        void detachView();

        V getView();

        void onViewCreate();

        void onViewDestroy();

        void onViewStart();

        void onViewStop();
    }
}