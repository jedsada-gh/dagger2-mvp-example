package com.wisdomlanna.www.dagger2_mvp_example.main;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.wisdomlanna.www.dagger2_mvp_example.ApplicationComponent;
import com.wisdomlanna.www.dagger2_mvp_example.R;
import com.wisdomlanna.www.dagger2_mvp_example.R2;
import com.wisdomlanna.www.dagger2_mvp_example.base.BaseActivity;
import com.wisdomlanna.www.dagger2_mvp_example.dao.UserInfoDao;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R2.id.tvResult)
    TextView tvResult;

    @Inject
    SharedPreferences sharedPreferences;

    private int result;

    @Override
    protected int layoutToInflate() {
        return R.layout.activity_main;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().loadUserInfoGitHub("pondthaitay");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            getPresenter().plus(5, 5);

        sharedPreferences.edit().putString("kk", "Jedsada").apply();
        Timber.d(sharedPreferences.getString("kk", ""));
    }

    @Override
    protected void doInjection(ApplicationComponent component) {
        component.inject(this);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void showResultPlus(int result) {
        this.result = result;
        tvResult.setText(String.format("result : %d", result));
    }

    @Override
    public void showResultUserInfoGitHubApi(UserInfoDao dao) {
        Timber.d("result userName : %s", dao.getName());
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
        getPresenter().restoreResultPlus(result);
    }
}
