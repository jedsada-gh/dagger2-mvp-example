package com.wisdomlanna.www.dagger2_mvp_example.api;

import com.wisdomlanna.www.dagger2_mvp_example.dao.UserInfoDao;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

import static dagger.internal.Preconditions.checkNotNull;

public class GitHubApiDataSource implements GitHubDataSource {

    private GitHubApi gitHubApi;

    @Inject
    public GitHubApiDataSource(GitHubApi gitHubApi) {
        this.gitHubApi = checkNotNull(gitHubApi);
    }

    @Override
    public Observable<Response<UserInfoDao>> weather(String userName) {
        return gitHubApi.getUserInfo(userName);
    }
}
