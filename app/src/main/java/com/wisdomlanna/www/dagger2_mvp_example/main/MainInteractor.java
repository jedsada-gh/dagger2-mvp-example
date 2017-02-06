package com.wisdomlanna.www.dagger2_mvp_example.main;

import com.wisdomlanna.www.dagger2_mvp_example.dao.UserInfoDao;

interface MainInteractor {

    interface OnValidatePlusListener {
        void setOnError(String message);

        void setOnPlusSuccess(int result);
    }

    interface OnUserInfoGitHubListener {
        void onSuccess(UserInfoDao dao);

        void onError(String message);

        void onFailure(String message);
    }

    void validatePlus(OnValidatePlusListener listener, int... numbers);

    void loadUserInfoGitHub(String userName, OnUserInfoGitHubListener listener);
}
