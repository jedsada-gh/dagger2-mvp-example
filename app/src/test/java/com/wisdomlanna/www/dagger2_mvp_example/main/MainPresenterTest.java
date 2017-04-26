package com.wisdomlanna.www.dagger2_mvp_example.main;

import android.util.Log;

import com.wisdomlanna.www.dagger2_mvp_example.api.GitHubApi;
import com.wisdomlanna.www.dagger2_mvp_example.ui.MainInterface;
import com.wisdomlanna.www.dagger2_mvp_example.ui.MainPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class MainPresenterTest {

    @Mock
    MainInterface.View mockView;
    MainPresenter presenter;
    MainPresenter spyPresenter;
    GitHubApi gitHubApi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(gitHubApi);
        presenter.attachView(mockView);
        spyPresenter = spy(presenter);
        spyPresenter.attachView(mockView);
    }

    @After
    public void destroy() {
        presenter.detachView();
    }

    @Test
    public void plus() throws Exception {
        presenter.plus(5, 5);
        verify(mockView, times(1)).showProgressDialog();
        verify(mockView, times(1)).hideProgressDialog();
        verify(mockView, times(1)).showResultPlus(eq(10));
        assertThat(10, is(10));
    }

    @Test
    public void loadUserInfoGitHub() throws Exception {
//        presenter.loadUserInfoGitHub("pondthaitay");
//        verify(view).showProgressDialog();
    }

    @Test
    public void setOnError() throws Exception {
//        presenter.setOnError("error");
//        verify(view).hideProgressDialog();
//        verify(view).showError(eq("error"));
    }

    @Test
    public void onSuccess() throws Exception {
//        UserInfoDao userInfoDao = new UserInfoDao();
//        presenter.onSuccess(userInfoDao);
//        verify(view).hideProgressDialog();
//        verify(view).showResultUserInfoGitHubApi(eq(userInfoDao));
    }
}