package com.wisdomlanna.www.dagger2_mvp_example.main.utils;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxSchedulersOverrideRule implements TestRule {

    private final RxAndroidSchedulersHook mRxAndroidSchedulersHook = new RxAndroidSchedulersHook() {
        @Override
        public rx.Scheduler getMainThreadScheduler() {
            return Schedulers.io();
        }
    };

    private final Func1<Scheduler, Scheduler> mRxJavaImmediateScheduler =
            new Func1<Scheduler, Scheduler>() {
                @Override
                public Scheduler call(Scheduler scheduler) {
                    return Schedulers.immediate();
                }
            };

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxAndroidPlugins.getInstance().reset();
                RxAndroidPlugins.getInstance().registerSchedulersHook(mRxAndroidSchedulersHook);

                RxJavaHooks.reset();
                RxJavaHooks.setOnIOScheduler(mRxJavaImmediateScheduler);
                RxJavaHooks.setOnNewThreadScheduler(mRxJavaImmediateScheduler);

                base.evaluate();

                RxAndroidPlugins.getInstance().reset();
                RxJavaHooks.reset();
            }
        };
    }
}