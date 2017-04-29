package com.wisdomlanna.www.dagger2_mvp_example.ui;

import com.wisdomlanna.www.dagger2_mvp_example.api.dao.UserInfoDao;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseInterface;
import com.wisdomlanna.www.dagger2_mvp_example.ui.event.TestBusEvent;

class MainInterface {

    interface View extends BaseInterface.View {
        void showResultPlus(int result);

        void showResultUserInfoGitHubApi(UserInfoDao dao);

        void showResultBusTag(int result);

        void showResultBusTestBusEvent(TestBusEvent event);
    }

    interface Presenter {
        void plus(String x, String y);

        void loadUserInfo(String username);
    }
}