package com.wisdomlanna.www.dagger2_mvp_example;

import android.app.Application;

import com.wisdomlanna.www.dagger2_mvp_example.module.AndroidModule;
import com.wisdomlanna.www.dagger2_mvp_example.module.ApiModule;
import com.wisdomlanna.www.dagger2_mvp_example.module.NetworkModule;
import com.wisdomlanna.www.dagger2_mvp_example.module.UtilModule;

import butterknife.ButterKnife;
import timber.log.Timber;

public class MyApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        ButterKnife.setDebug(BuildConfig.DEBUG);
        initDependencyInjection();
        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());
    }

    private void initDependencyInjection() {
        component = DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .utilModule(new UtilModule())
                .networkModule(new NetworkModule())
                .apiModule(new ApiModule())
                .build();
        component().inject(this);
    }

    public ApplicationComponent component() {
        return component;
    }
}