package com.wisdomlanna.www.dagger2_mvp_example.module;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.wisdomlanna.www.dagger2_mvp_example.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilModule {
    private MyApplication myApplication;

    public UtilModule(MyApplication application) {
        this.myApplication = application;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(myApplication);
    }
}
