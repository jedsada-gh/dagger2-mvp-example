package com.wisdomlanna.www.dagger2_mvp_example.template.activity;

import android.os.Bundle;

import com.wisdomlanna.www.dagger2_mvp_example.ApplicationComponent;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseActivity;

public class CustomActivity extends BaseActivity<CustomActivityPresenter>
        implements CustomActivityInterface.View {
    @Override
    public void testResult() {

    }

    @Override
    protected int layoutToInflate() {
        return 0;
    }

    @Override
    protected void doInjection(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    protected void startView() {

    }

    @Override
    protected void stopView() {

    }

    @Override
    protected void bindView() {

    }

    @Override
    protected void setupInstance() {

    }

    @Override
    protected void setupView() {

    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void saveInstanceState(Bundle outState) {

    }

    @Override
    public void restoreView(Bundle savedInstanceState) {

    }
}
