package com.wisdomlanna.www.dagger2_mvp_example.api;

import com.wisdomlanna.www.dagger2_mvp_example.dao.UserInfoDao;

import io.reactivex.Observable;
import retrofit2.Response;

public interface GitHubDataSource {
    Observable<Response<UserInfoDao>> weather(String userName);
}
