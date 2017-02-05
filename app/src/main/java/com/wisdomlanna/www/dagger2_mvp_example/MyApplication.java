package com.wisdomlanna.www.dagger2_mvp_example;

import android.app.Application;

import butterknife.ButterKnife;

public class MyApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        ButterKnife.setDebug(BuildConfig.DEBUG);
        initDependencyInjection();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    private void initDependencyInjection() {
        component = DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .utilModule(new UtilModule(this))
                .build();
        getComponent().inject(this);
    }
}