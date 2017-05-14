package com.wisdomlanna.www.dagger2_mvp_example.template.frangment;

import android.os.Bundle;
import android.view.View;

import com.wisdomlanna.www.dagger2_mvp_example.ApplicationComponent;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseFragment;

public class CustomFragment extends BaseFragment<CustomFragmentPresenter>
        implements CustomFragmentInterface.View {

    public static CustomFragment newInstance() {
        CustomFragment fragment = new CustomFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int layoutToInflate() {
        return 0;
    }

    @Override
    protected void doInjection(ApplicationComponent component) {

    }

    @Override
    protected void startView() {

    }

    @Override
    protected void stopView() {

    }

    @Override
    protected void bindView(View view) {

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