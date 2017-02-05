package com.wisdomlanna.www.dagger2_mvp_example.main;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class MainInteractorImpl implements MainInteractor {

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
}