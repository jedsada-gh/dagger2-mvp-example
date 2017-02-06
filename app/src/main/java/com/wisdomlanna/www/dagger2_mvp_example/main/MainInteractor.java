package com.wisdomlanna.www.dagger2_mvp_example.main;

import com.wisdomlanna.www.dagger2_mvp_example.base.ConnectionCallback;

interface MainInteractor {

    interface OnValidatePlusListener {
        void setOnError(String message);

        void setOnPlusSuccess(int result);
    }

    void validatePlus(OnValidatePlusListener listener, int... numbers);

    void loadUserInfoGitHub(String userName, ConnectionCallback callback);
}
