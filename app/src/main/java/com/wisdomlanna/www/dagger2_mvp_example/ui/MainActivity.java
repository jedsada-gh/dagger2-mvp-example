package com.wisdomlanna.www.dagger2_mvp_example.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.wisdomlanna.www.dagger2_mvp_example.ApplicationComponent;
import com.wisdomlanna.www.dagger2_mvp_example.R;
import com.wisdomlanna.www.dagger2_mvp_example.R2;
import com.wisdomlanna.www.dagger2_mvp_example.api.dao.UserInfoDao;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class MainActivity extends BaseActivity<MainPresenter> implements MainInterface.View {

    @BindView(R2.id.tvResult)
    TextView tvResult;
    @BindView(R.id.tvUsername)
    TextView tvUsername;

    @Inject
    SharedPreferences sharedPreferences;

    private int result;

    @SuppressLint("DefaultLocale")
    @Override
    public void showResultPlus(int result) {
        this.result = result;
        tvResult.setText(String.format("result : %d", result));
    }

    @Override
    public void showResultUserInfoGitHubApi(UserInfoDao dao) {
        Timber.d("result userName : %s", dao.getName());
        tvUsername.setText(dao.getName());
    }

    @Override
    protected int layoutToInflate() {
        return R.layout.activity_main;
    }

    @Override
    protected void doInjection(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    protected void startActivity() {
        getPresenter().loadUserInfo("pondthaitay");
    }

    @Override
    protected void stopActivity() {
        Timber.d("stop activity");
    }

    @Override
    protected void bindView() {
        Timber.d("bind view");
    }

    @Override
    protected void setupInstance() {
        sharedPreferences.edit().putString("kk", "Jedsada").apply();
        Timber.d(sharedPreferences.getString("kk", ""));
    }

    @Override
    protected void setupView() {
        Timber.d("setup view");
    }

    @Override
    protected void initialize() {
        getPresenter().plus("5", "5");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("result", result);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        result = savedInstanceState.getInt("result");
        getPresenter().plus(String.valueOf(result), "0");
    }
}