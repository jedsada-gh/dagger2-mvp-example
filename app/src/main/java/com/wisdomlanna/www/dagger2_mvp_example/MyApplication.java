package com.wisdomlanna.www.dagger2_mvp_example;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import com.wisdomlanna.www.dagger2_mvp_example.module.AndroidModule;
import com.wisdomlanna.www.dagger2_mvp_example.module.ApiModule;
import com.wisdomlanna.www.dagger2_mvp_example.module.NetworkModule;
import com.wisdomlanna.www.dagger2_mvp_example.module.UtilModule;

import butterknife.ButterKnife;
import timber.log.Timber;

public class MyApplication extends Application {

    private ApplicationComponent component;

    @SuppressLint("ObsoleteSdkInt")
    @Override
    public void onCreate() {
        super.onCreate();
        ButterKnife.setDebug(BuildConfig.DEBUG);
        initDependencyInjection();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
            StrictMode.ThreadPolicy.Builder threadPolicyBuilder = new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                threadPolicyBuilder.penaltyDeathOnNetwork();
            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
        }
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