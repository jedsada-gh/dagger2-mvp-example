package com.wisdomlanna.www.dagger2_mvp_example.module;

import com.wisdomlanna.www.dagger2_mvp_example.api.service.GitHubApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @Singleton
    GitHubApi providesGithubService(Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }
}