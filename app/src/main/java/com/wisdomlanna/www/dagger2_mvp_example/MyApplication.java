package com.wisdomlanna.www.dagger2_mvp_example;

import android.app.Application;

import com.wisdomlanna.www.dagger2_mvp_example.module.AndroidModule;
import com.wisdomlanna.www.dagger2_mvp_example.module.NetworkModule;
import com.wisdomlanna.www.dagger2_mvp_example.module.UtilModule;

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
                .networkModule(new NetworkModule())
                .build();
        getComponent().inject(this);
    }
}