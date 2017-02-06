package com.wisdomlanna.www.dagger2_mvp_example.main;

import com.wisdomlanna.www.dagger2_mvp_example.api.GitHubApiDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
class MainInteractorImpl implements MainInteractor {

    @Inject
    GitHubApiDataSource gitHubApi;

    @Inject
    MainInteractorImpl() {
        super();
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
    public void loadUserInfoGitHub(String userName) {
        gitHubApi.weather(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userInfoDaoResponse -> {

                });
    }
}
