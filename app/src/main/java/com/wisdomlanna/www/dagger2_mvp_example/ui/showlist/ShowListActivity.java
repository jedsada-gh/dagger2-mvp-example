package com.wisdomlanna.www.dagger2_mvp_example.ui.showlist;

import android.os.Bundle;

import com.wisdomlanna.www.dagger2_mvp_example.ApplicationComponent;
import com.wisdomlanna.www.dagger2_mvp_example.R;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseActivity;

public class ShowListActivity extends BaseActivity<ShowListActivityPresenter>
        implements ShowListInterface.View {
    @Override
    public void testResult() {

    }

    @Override
    protected int layoutToInflate() {
        return R.layout.activity_show_list;
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
