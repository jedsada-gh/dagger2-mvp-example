package com.wisdomlanna.www.dagger2_mvp_example.ui.main.frangment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.wisdomlanna.www.dagger2_mvp_example.ApplicationComponent;
import com.wisdomlanna.www.dagger2_mvp_example.R;
import com.wisdomlanna.www.dagger2_mvp_example.Utils;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseFragment;
import com.wisdomlanna.www.dagger2_mvp_example.ui.showlist.ShowListActivity;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class MainFragment extends BaseFragment<MainFragmentPresenter>
        implements MainFragmentInterface.View {

    @Inject
    Utils utils;

    @BindView(R.id.tv_test)
    TextView tvTest;
    @BindView(R.id.btn_show_list)
    AppCompatButton btnShoList;

    private int number;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
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
        return R.layout.main_fragment;
    }

    @Override
    protected void doInjection(ApplicationComponent component) {
        component.inject(MainFragment.this);
    }

    @Override
    protected void startView() {

    }

    @Override
    protected void stopView() {

    }

    @Override
    protected void bindView(View view) {
        btnShoList.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), ShowListActivity.class)));
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