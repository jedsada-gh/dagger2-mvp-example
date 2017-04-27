package com.wisdomlanna.www.dagger2_mvp_example.main;

import android.util.Log;

import com.wisdomlanna.www.dagger2_mvp_example.api.BaseSubscriber;
import com.wisdomlanna.www.dagger2_mvp_example.api.GitHubApi;
import com.wisdomlanna.www.dagger2_mvp_example.api.GithubManager;
import com.wisdomlanna.www.dagger2_mvp_example.api.dao.UserInfoDao;
import com.wisdomlanna.www.dagger2_mvp_example.main.utils.JsonMockUtility;
import com.wisdomlanna.www.dagger2_mvp_example.ui.MainInterface;
import com.wisdomlanna.www.dagger2_mvp_example.ui.MainPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Action1;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class MainPresenterTest {

    @Mock
    private MainInterface.View mockView;
    @Mock
    private GitHubApi gitHubApi;
    @Mock
    private Observable<Response<UserInfoDao>> mockCall;
    @Captor
    private ArgumentCaptor<Action1<Response<UserInfoDao>>> consumerArgumentCaptor;
    @Captor
    private ArgumentCaptor<BaseSubscriber<UserInfoDao>> captor1;

    private MainPresenter presenter;
    private JsonMockUtility jsonUtil;
    private GithubManager spyGithubManager;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        jsonUtil = new JsonMockUtility();

        spyGithubManager = new GithubManager(gitHubApi);

        presenter = new MainPresenter(spy(spyGithubManager));
        presenter.attachView(mockView);
        MainPresenter spyPresenter = spy(presenter);
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

    @SuppressWarnings("unchecked")
    @Test
    public void loadUserInfoGitHub() throws Exception {
        UserInfoDao mockResult = jsonUtil.getJsonToMock(
                "user_info_success.json",
                UserInfoDao.class);

        Response<UserInfoDao> mockResponse = Response.success(mockResult);
        when(spyGithubManager.getUserInfo(anyString())).thenReturn(mockCall);
        presenter.loadUserInfo("pondthaitay");
        mockCall.subscribe(captor1.capture());
        verify(mockView, times(1)).showProgressDialog();
        verify(mockCall).subscribe(captor1.capture());
//        networkCallbackArgumentCaptor.getValue().onUnAuthorized();

//        verify(mockView, times(1)).hideProgressDialog();
//        verify(mockView, times(1)).showResultUserInfoGitHubApi(eq(mockResponse.body()));
        assertThat(200, is(mockResponse.code()));
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