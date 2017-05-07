package com.wisdomlanna.www.dagger2_mvp_example.template.frangment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wisdomlanna.www.dagger2_mvp_example.ApplicationComponent;
import com.wisdomlanna.www.dagger2_mvp_example.R;
import com.wisdomlanna.www.dagger2_mvp_example.Utils;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class CustomFragment extends BaseFragment<CustomFragmentPresenter>
        implements CustomFragmentInterface.View {

    @Inject
    Utils utils;

    @BindView(R.id.tv_test)
    TextView tvTest;
    private int number;

    public static CustomFragment newInstance() {
        CustomFragment fragment = new CustomFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void testResult() {
        Timber.d("status network : %b", utils.isNetworkAvailable());
        tvTest.setText(String.format("test : %d", number));
    }

    @Override
    protected int layoutToInflate() {
        return R.layout.custom_fragment;
    }

    @Override
    protected void doInjection(ApplicationComponent component) {
        component.inject(CustomFragment.this);
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
        Timber.d("initialize");
        number = 5555;
        getPresenter().testFragment();
    }

    @Override
    protected void saveInstanceState(Bundle outState) {
        outState.putInt("number", number);
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        number = savedInstanceState.getInt("number");
        testResult();
    }
}