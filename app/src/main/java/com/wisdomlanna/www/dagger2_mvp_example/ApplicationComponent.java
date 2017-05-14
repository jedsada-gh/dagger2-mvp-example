package com.wisdomlanna.www.dagger2_mvp_example;

import com.wisdomlanna.www.dagger2_mvp_example.module.AndroidModule;
import com.wisdomlanna.www.dagger2_mvp_example.module.ApiModule;
import com.wisdomlanna.www.dagger2_mvp_example.module.NetworkModule;
import com.wisdomlanna.www.dagger2_mvp_example.module.UtilModule;
import com.wisdomlanna.www.dagger2_mvp_example.ui.main.MainActivity;
import com.wisdomlanna.www.dagger2_mvp_example.ui.main.frangment.MainFragment;
import com.wisdomlanna.www.dagger2_mvp_example.ui.showlist.ShowListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UtilModule.class, AndroidModule.class, NetworkModule.class, ApiModule.class})
public interface ApplicationComponent {
    void inject(MyApplication application);

    void inject(MainActivity mainActivity);

    void inject(MainFragment mainFragment);

    void inject(ShowListActivity showListActivity);
}