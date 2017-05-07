package com.wisdomlanna.www.dagger2_mvp_example.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wisdomlanna.www.dagger2_mvp_example.ApplicationComponent;
import com.wisdomlanna.www.dagger2_mvp_example.MyApplication;
import com.wisdomlanna.www.dagger2_mvp_example.R;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.exception.MvpNotSetLayoutException;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.exception.MvpPresenterNotCreateException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseFragment<P extends BaseInterface.Presenter> extends Fragment
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

    protected abstract void startView();

    protected abstract void stopView();

    protected abstract void bindView(View view);

    protected abstract void setupInstance();

    protected abstract void setupView();

    protected abstract void initialize();

    protected abstract void saveInstanceState(Bundle outState);

    public abstract void restoreView(Bundle savedInstanceState);

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (layoutToInflate() == 0) throw new MvpNotSetLayoutException();
        doInjection(((MyApplication) getActivity().getApplicationContext()).component());
        presenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (layoutToInflate() == 0) throw new MvpNotSetLayoutException();
        View view = inflater.inflate(layoutToInflate(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        setupInstance();
        setupView();
        setupProgressDialog();
        getPresenter().onViewCreate();
        if (savedInstanceState == null) initialize();
        else restoreView(savedInstanceState);
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
    public void onStart() {
        super.onStart();
        startView();
        getPresenter().onViewStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopView();
        getPresenter().onViewStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (progressDialog != null) progressDialog.cancel();
        getPresenter().onViewDestroy();
        presenter.detachView();
    }

    @Override
    public P getPresenter() {
        if (presenter != null) return presenter;
        throw new MvpPresenterNotCreateException();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveInstanceState(outState);
    }

    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.loading));
    }

    private void showSnackBar(@NonNull String message) {
        if (contentView != null) Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }
}
