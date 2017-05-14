package com.wisdomlanna.www.dagger2_mvp_example.ui.main;

import com.wisdomlanna.www.dagger2_mvp_example.api.dao.UserInfoDao;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseInterface;
import com.wisdomlanna.www.dagger2_mvp_example.ui.event.TestBusEvent;

public class MainInterface {

    public interface View extends BaseInterface.View {
        void showResultPlus(int result);

        void showResultUserInfoGitHubApi(UserInfoDao dao);

        void showResultBusTag(int result);

        void showResultBusTestBusEvent(TestBusEvent event);
    }

    public interface Presenter {
        void plus(String x, String y);

        void loadUserInfo(String username);
    }
}