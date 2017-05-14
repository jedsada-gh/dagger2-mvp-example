package com.wisdomlanna.www.dagger2_mvp_example.ui.main;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wisdomlanna.www.dagger2_mvp_example.ApplicationComponent;
import com.wisdomlanna.www.dagger2_mvp_example.R;
import com.wisdomlanna.www.dagger2_mvp_example.R2;
import com.wisdomlanna.www.dagger2_mvp_example.api.dao.UserInfoDao;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseActivity;
import com.wisdomlanna.www.dagger2_mvp_example.ui.event.TestBusEvent;
import com.wisdomlanna.www.dagger2_mvp_example.ui.main.frangment.MainFragment;

import org.parceler.Parcels;

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
    @Inject
    Gson gson;

    private UserInfoDao userInfoDao;
    private int result;

    @SuppressLint("DefaultLocale")
    @Override
    public void showResultPlus(int result) {
        this.result = result;
        tvResult.setText(String.format("result : %d", result));
    }

    @Override
    public void showResultUserInfoGitHubApi(UserInfoDao dao) {
        userInfoDao = dao;
        tvUsername.setText(dao.getName());
    }

    @Override
    public void showResultBusTag(int result) {
        Timber.d("result bus tag : %d", result);
    }

    @Override
    public void showResultBusTestBusEvent(TestBusEvent event) {
        Timber.d("result bus TestBusEvent : %s", gson.toJson(event));
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
    protected void startView() {
        Timber.d("start activity");
        getPresenter().onViewStart();
    }

    @Override
    protected void stopView() {
        Timber.d("stop activity");
        getPresenter().onViewStop();
    }

    @Override
    protected void bindView() {
        Timber.d("bind view");
    }

    @Override
    protected void setupInstance() {
        sharedPreferences.edit().putString("kk", "Jedsada").apply();
        Timber.d("result shared preferences : %s", sharedPreferences.getString("kk", ""));
    }

    @Override
    protected void setupView() {
        Timber.d("setup view");
    }

    @Override
    protected void initialize() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_fragment, MainFragment.newInstance())
                .commit();
        getPresenter().loadUserInfo("pondthaitay");
        getPresenter().plus("5", "5");
    }

    @Override
    protected void saveInstanceState(Bundle outState) {
        outState.putInt("result", result);
        outState.putParcelable("user_info_dao", Parcels.wrap(userInfoDao));
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        result = savedInstanceState.getInt("result");
        userInfoDao = Parcels.unwrap(savedInstanceState.getParcelable("user_info_dao"));
        if (userInfoDao == null) getPresenter().loadUserInfo("pondthaitay");
        else showResultUserInfoGitHubApi(userInfoDao);
        showResultPlus(result);
    }
}