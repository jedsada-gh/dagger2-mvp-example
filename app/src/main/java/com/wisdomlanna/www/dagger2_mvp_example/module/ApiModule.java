package com.wisdomlanna.www.dagger2_mvp_example.module;

import com.wisdomlanna.www.dagger2_mvp_example.api.GitHubApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Singleton
    @Provides
    GitHubApi provideGitHubApi(Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }
}