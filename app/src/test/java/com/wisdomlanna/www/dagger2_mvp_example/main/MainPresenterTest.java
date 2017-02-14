package com.wisdomlanna.www.dagger2_mvp_example.main;

import com.wisdomlanna.www.dagger2_mvp_example.api.GitHubApi;
import com.wisdomlanna.www.dagger2_mvp_example.base.ConnectionCallback;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Scheduler;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainView view;
    @Mock
    GitHubApi api;
    @Mock
    ConnectionCallback callback;
    @Mock
    Scheduler scheduler;
    @InjectMocks
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

    }

    @Test
    public void setOnPlusSuccess() throws Exception {
        presenter.plus(5,5);
        verify(view).showProgressDialog();
        verify(view).hideProgressDialog();
        verify(view).showResultPlus(10);
    }

    @Test
    public void onSuccess() throws Exception {

    }
}