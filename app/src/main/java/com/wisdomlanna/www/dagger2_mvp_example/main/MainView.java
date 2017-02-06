package com.wisdomlanna.www.dagger2_mvp_example.main;

import com.wisdomlanna.www.dagger2_mvp_example.base.BaseView;
import com.wisdomlanna.www.dagger2_mvp_example.dao.UserInfoDao;

interface MainView extends BaseView {

    void showResultPlus(int result);

    void showResultUserInfoGitHubApi(UserInfoDao dao);
}
