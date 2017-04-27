package com.wisdomlanna.www.dagger2_mvp_example.api;

import com.wisdomlanna.www.dagger2_mvp_example.api.dao.UserInfoDao;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GitHubApi {

    @GET("users/{userName}")
    Observable<Response<UserInfoDao>> getUserInfo(@Path("userName") String userName);
}