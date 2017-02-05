package com.wisdomlanna.www.dagger2_mvp_example;

import android.content.Context;
import android.content.res.Resources;
import android.location.LocationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.LOCATION_SERVICE;

@Module
class AndroidModule {
    private final MyApplication application;

    AndroidModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager() {
        return (LocationManager) application.getSystemService(LOCATION_SERVICE);
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }
}