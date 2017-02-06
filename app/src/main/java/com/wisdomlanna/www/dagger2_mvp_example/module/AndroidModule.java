package com.wisdomlanna.www.dagger2_mvp_example.module;

import android.content.Context;
import android.content.res.Resources;
import android.location.LocationManager;

import com.wisdomlanna.www.dagger2_mvp_example.MyApplication;
import com.wisdomlanna.www.dagger2_mvp_example.configuration.BuildConfiguration;
import com.wisdomlanna.www.dagger2_mvp_example.configuration.Config;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.LOCATION_SERVICE;

@Module
public class AndroidModule {
    private final MyApplication application;

    public AndroidModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
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

    @Provides
    @Singleton
    Config provideConfig() {
        return new BuildConfiguration();
    }
}