package com.wisdomlanna.www.dagger2_mvp_example;

import android.app.Application;

public class MyApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initDependencyInjection();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    private void initDependencyInjection() {
        component = DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .utilModule(new UtilModule())
                .build();
        getComponent().inject(this);
    }
}