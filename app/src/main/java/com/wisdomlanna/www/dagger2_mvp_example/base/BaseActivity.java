package com.wisdomlanna.www.dagger2_mvp_example.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.wisdomlanna.www.dagger2_mvp_example.ApplicationComponent;
import com.wisdomlanna.www.dagger2_mvp_example.MyApplication;
import com.wisdomlanna.www.dagger2_mvp_example.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    @Inject
    P presenter;

    @Nullable
    @BindView(android.R.id.content)
    android.view.View contentView;

    private ProgressDialog progressDialog;

    @LayoutRes
    protected abstract int layoutToInflate();

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutToInflate());
        ButterKnife.bind(this);
        doInjection(((MyApplication) getApplication()).getComponent());
        presenter.attachView(this);
        setupProgressDialog();
    }

    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.loading));
    }

    protected abstract void doInjection(final ApplicationComponent component);

    public P getPresenter() {
        return presenter;
    }

    @Override
    public void showProgressDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void showError(String errorMessage) {
        if (contentView != null) {
            Snackbar.make(contentView, errorMessage, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void unAuthorizedApi() {
        if (contentView != null) {
            Snackbar.make(contentView, getResources().getString(R.string.un_authorize_api), Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        if(progressDialog != null)
            progressDialog.cancel();

        presenter.detachView();
        super.onDestroy();
    }
}