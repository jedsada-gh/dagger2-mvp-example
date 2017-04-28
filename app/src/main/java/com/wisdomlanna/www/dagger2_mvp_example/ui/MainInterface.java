package com.wisdomlanna.www.dagger2_mvp_example.ui;

import com.wisdomlanna.www.dagger2_mvp_example.api.dao.UserInfoDao;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseInterface;

class MainInterface {

    interface View extends BaseInterface.View {
        void showResultPlus(int result);

        void showResultUserInfoGitHubApi(UserInfoDao dao);
    }

    interface Presenter {
        void plus(String x, String y);

        void loadUserInfo(String username);
    }
}