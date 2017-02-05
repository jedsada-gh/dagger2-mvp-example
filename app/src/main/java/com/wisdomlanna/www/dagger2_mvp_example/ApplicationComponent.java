package com.wisdomlanna.www.dagger2_mvp_example;

import com.wisdomlanna.www.dagger2_mvp_example.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UtilModule.class, AndroidModule.class})
public interface ApplicationComponent {
    void inject(MyApplication application);

    void inject(MainActivity mainActivity);
}