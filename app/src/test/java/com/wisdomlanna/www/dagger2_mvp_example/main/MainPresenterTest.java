package com.wisdomlanna.www.dagger2_mvp_example.main;

import com.wisdomlanna.www.dagger2_mvp_example.api.dao.UserInfoDao;
import com.wisdomlanna.www.dagger2_mvp_example.api.manager.GithubManager;
import com.wisdomlanna.www.dagger2_mvp_example.api.service.GitHubApi;
import com.wisdomlanna.www.dagger2_mvp_example.main.utils.JsonMockUtility;
import com.wisdomlanna.www.dagger2_mvp_example.main.utils.RxSchedulersOverrideRule;
import com.wisdomlanna.www.dagger2_mvp_example.ui.MainInterface;
import com.wisdomlanna.www.dagger2_mvp_example.ui.MainPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class MainPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule schedulers = new RxSchedulersOverrideRule();

    @Mock
    private MainInterface.View mockView;
    @Mock
    private GitHubApi gitHubApi;
    @Mock
    private Observable<Response<UserInfoDao>> mockCall;

    private MainPresenter presenter;
    private JsonMockUtility jsonUtil;
    private GithubManager spyGithubManager;

    @Captor
    private ArgumentCaptor<Observer<Response<UserInfoDao>>> retrofitCallBack;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        jsonUtil = new JsonMockUtility();

        GithubManager githubManager = new GithubManager(gitHubApi);
        spyGithubManager = spy(githubManager);

        presenter = new MainPresenter(gitHubApi);
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

    @Test
    public void loadUserInfoGitHub() throws Exception {
        UserInfoDao mockResult = jsonUtil.getJsonToMock(
                "user_info_success.json",
                UserInfoDao.class);

        Response<UserInfoDao> mockResponse = Response.success(mockResult);
        mockCall = Observable.just(mockResponse);
        retrofitCallBack = new ArgumentCaptor<>();
        when(gitHubApi.getUserInfo(anyString())).thenReturn(mockCall);
        presenter.loadUserInfo("pondthaitay");
        mockCall.subscribe();
    }

    @Test
    public void setOnError() throws Exception {
        presenter.callX();
        verify(mockView).showX(eq(spyGithubManager.getX()));
        assertThat(20, is(spyGithubManager.getX()));
    }

    @Test
    public void onSuccess() throws Exception {

    }
}