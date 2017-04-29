package com.wisdomlanna.www.dagger2_mvp_example.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.wisdomlanna.www.dagger2_mvp_example.ApplicationComponent;
import com.wisdomlanna.www.dagger2_mvp_example.MyApplication;
import com.wisdomlanna.www.dagger2_mvp_example.R;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.exception.MvpNotSetLayoutException;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.exception.MvpPresenterNotCreateException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity
        implements BaseInterface.View {

    @Inject
    P presenter;

    @Nullable
    @BindView(android.R.id.content)
    android.view.View contentView;

    private ProgressDialog progressDialog;

    @LayoutRes
    protected abstract int layoutToInflate();

    protected abstract void doInjection(final ApplicationComponent component);

    protected abstract void startActivity();

    protected abstract void stopActivity();

    protected abstract void bindView();

    protected abstract void setupInstance();

    protected abstract void setupView();

    protected abstract void initialize();

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (layoutToInflate() == 0) throw new MvpNotSetLayoutException();
        setContentView(layoutToInflate());
        doInjection(((MyApplication) getApplication()).component());
        ButterKnife.bind(this);
        presenter.attachView(this);
        bindView();
        setupInstance();
        setupView();
        setupProgressDialog();
        getPresenter().onViewCreate();
        if (savedInstanceState == null) initialize();
    }

    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.loading));
    }

    public P getPresenter() {
        if (presenter != null) return presenter;
        throw new MvpPresenterNotCreateException();
    }

    @Override
    public void showProgressDialog() {
        if (!progressDialog.isShowing()) progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null) progressDialog.dismiss();
    }

    @Override
    public void showError(String errorMessage) {
        showSnackBar(errorMessage);
    }

    @Override
    public void showError(@StringRes int errorMessage) {
        showSnackBar(getString(errorMessage));
    }

    @Override
    public void showMessage(String message) {
        showSnackBar(message);
    }

    @Override
    public void showMessage(@StringRes int message) {
        showSnackBar(getString(message));
    }

    @Override
    public void unAuthorizedApi() {
        showSnackBar(getResources().getString(R.string.un_authorize_api));
    }

    @Override
    protected void onStart() {
        super.onStart();
        startActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopActivity();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null) progressDialog.cancel();
        getPresenter().onViewDestroy();
        presenter.detachView();
    }

    private void showSnackBar(@NonNull String message) {
        if (contentView != null) Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }
}