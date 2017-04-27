package com.wisdomlanna.www.dagger2_mvp_example.api.manager;

import com.wisdomlanna.www.dagger2_mvp_example.api.BaseSubscriber;
import com.wisdomlanna.www.dagger2_mvp_example.api.dao.UserInfoDao;
import com.wisdomlanna.www.dagger2_mvp_example.api.service.GitHubApi;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class GithubManager {

    private GitHubApi api;
    private int x = 5;

    public GithubManager(GitHubApi gitHubApi) {
        this.api = gitHubApi;
    }

    public Observable<Response<UserInfoDao>> getUserInfoObservable(String username) {
        return api.getUserInfo(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void getUserInfo(String username, BaseSubscriber.NetworkCallback callback) {
        getUserInfoObservable(username).subscribe(new BaseSubscriber<>(callback));
    }

    public void setX() {
        x += 15;
    }

    public int getX() {
        return x;
    }
}