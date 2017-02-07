package com.wisdomlanna.www.dagger2_mvp_example.configuration;

import com.wisdomlanna.www.dagger2_mvp_example.BuildConfig;

public class BuildConfiguration implements Config {
    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public String version() {
        return BuildConfig.VERSION_NAME;
    }

    @Override
    public String userToken() {
        return null;
    }

    @Override
    public String serverUrl() {
        return BuildConfig.SEVER;
    }
}