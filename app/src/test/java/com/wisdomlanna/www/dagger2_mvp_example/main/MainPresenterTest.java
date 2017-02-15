package com.wisdomlanna.www.dagger2_mvp_example.main;

import com.wisdomlanna.www.dagger2_mvp_example.dao.UserInfoDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    @Mock
    MainView view;
    @Mock
    MainInteractorImpl interactor;

    private MainPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(interactor);
        presenter.attachView(view);
    }

    @After
    public void destroy(){
        presenter.detachView();
    }

    @Test
    public void plus() throws Exception {
        presenter.plus(5,5);
        verify(view).showProgressDialog();
    }

    @Test
    public void setOnPlusSuccess() throws Exception {
        presenter.setOnPlusSuccess(10);
        verify(view).hideProgressDialog();
        verify(view).showResultPlus(eq(10));
    }

    @Test
    public void restoreResultPlus() throws Exception {
        presenter.restoreResultPlus(10);
        verify(view).showResultPlus(eq(10));
    }

    @Test
    public void loadUserInfoGitHub() throws Exception {
        presenter.loadUserInfoGitHub("pondthaitay");
        verify(view).showProgressDialog();
    }

    @Test
    public void setOnError() throws Exception {
        presenter.setOnError("error");
        verify(view).hideProgressDialog();
        verify(view).showError(eq("error"));
    }

    @Test
    public void onSuccess() throws Exception {
        UserInfoDao userInfoDao = new UserInfoDao();
        presenter.onSuccess(userInfoDao);
        verify(view).hideProgressDialog();
        verify(view).showResultUserInfoGitHubApi(eq(userInfoDao));
    }
}