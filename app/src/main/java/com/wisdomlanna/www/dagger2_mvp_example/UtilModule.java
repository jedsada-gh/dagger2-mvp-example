package com.wisdomlanna.www.dagger2_mvp_example;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class UtilModule {
    private MyApplication myApplication;

    UtilModule(MyApplication application) {
        this.myApplication = application;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(myApplication);
    }
}
