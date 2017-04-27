package com.wisdomlanna.www.dagger2_mvp_example.api;

import com.wisdomlanna.www.dagger2_mvp_example.api.dao.UserInfoDao;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GithubManager {

    private GitHubApi api;

    public GithubManager(GitHubApi gitHubApi) {
        api = gitHubApi;
    }

    public Observable<Response<UserInfoDao>> getUserInfo(String username) {
        return api.getUserInfo(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void getUserInfo(String username, BaseSubscriber.NetworkCallback callback) {
        getUserInfo(username).subscribe(new BaseSubscriber<>(callback));
    }
}