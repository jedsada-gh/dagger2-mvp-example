package com.wisdomlanna.www.dagger2_mvp_example.main;

import com.wisdomlanna.www.dagger2_mvp_example.api.GitHubApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static dagger.internal.Preconditions.checkNotNull;

@Singleton
class MainInteractorImpl implements MainInteractor {

    private GitHubApi gitHubApi;

    @Inject
    MainInteractorImpl(GitHubApi gitHubApi) {
        super();
        this.gitHubApi = checkNotNull(gitHubApi);
    }

    @Override
    public void validatePlus(OnValidatePlusListener listener, int... numbers) {
        try {
            listener.setOnPlusSuccess(numbers[0] + numbers[1]);
        } catch (NumberFormatException e) {
            listener.setOnError(e.getMessage());
        }
    }

    @Override
    public void loadUserInfoGitHub(String userName, OnUserInfoGitHubListener listener) {
        gitHubApi.getUserInfo(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.isSuccessful())
                        listener.onSuccess(response.body());
                    else
                        listener.onError(response.message());
                }, throwable -> listener.onFailure(throwable.getMessage()));
    }
}