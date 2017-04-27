package com.wisdomlanna.www.dagger2_mvp_example.module;

import com.wisdomlanna.www.dagger2_mvp_example.api.GitHubApi;
import com.wisdomlanna.www.dagger2_mvp_example.api.GithubManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @Singleton
    GithubManager providesGithubService(Retrofit retrofit) {
        return new GithubManager(retrofit.create(GitHubApi.class));
    }
}