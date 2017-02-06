package com.wisdomlanna.www.dagger2_mvp_example.base;

public interface ConnectionCallback extends BaseView.ErrorCallback {

    <T> void onSuccess(T t);
}
