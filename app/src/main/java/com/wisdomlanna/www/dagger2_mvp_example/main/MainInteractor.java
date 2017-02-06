package com.wisdomlanna.www.dagger2_mvp_example.main;

import com.wisdomlanna.www.dagger2_mvp_example.base.ConnectionCallback;

interface MainInteractor {

    interface OnValidatePlusCallback {
        void setOnError(String message);

        void setOnPlusSuccess(int result);
    }

    void validatePlus(OnValidatePlusCallback callback, int... numbers);

    void loadUserInfoGitHub(String userName, ConnectionCallback callback);
}
