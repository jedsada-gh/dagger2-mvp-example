package com.wisdomlanna.www.dagger2_mvp_example.ui;

import android.util.Log;

import com.hwangjr.rxbus.Bus;
import com.hwangjr.rxbus.RxBus;
import com.wisdomlanna.www.dagger2_mvp_example.R;
import com.wisdomlanna.www.dagger2_mvp_example.api.BaseSubscriber;
import com.wisdomlanna.www.dagger2_mvp_example.api.dao.UserInfoDao;
import com.wisdomlanna.www.dagger2_mvp_example.api.service.GitHubApi;
import com.wisdomlanna.www.dagger2_mvp_example.manager.Calculator;
import com.wisdomlanna.www.dagger2_mvp_example.ui.event.TestBusEvent;
import com.wisdomlanna.www.dagger2_mvp_example.utils.JsonMockUtility;
import com.wisdomlanna.www.dagger2_mvp_example.utils.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.TestObserver;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class, RxBus.class, CompositeDisposable.class})
public class MainPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule schedulers = new RxSchedulersOverrideRule();
    @Mock
    private MainInterface.View mockView;
    @Mock
    private CompositeDisposable disposable;
    @Mock
    private GitHubApi gitHubApi;
    @Mock
    private Bus bus;

    private MainPresenter presenter;
    private JsonMockUtility jsonUtil;
    private ResponseBody responseBody;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockStatic(RxBus.class);

        responseBody = ResponseBody.create(MediaType.parse("application/json"), "");
        jsonUtil = new JsonMockUtility();

        Calculator calculator = new Calculator();
        Calculator spyCalculator = spy(calculator);

        presenter = new MainPresenter(gitHubApi, disposable, spyCalculator);
        presenter.attachView(mockView);

        when(RxBus.get()).thenReturn(bus);
    }

    @After
    public void destroy() {
        presenter.detachView();
    }

    @Test
    public void plus() throws Exception {
        presenter.plus("5", "5");
        verify(mockView, times(1)).showResultPlus(eq(10));
        assertThat(10, is(10));
    }

    @Test
    public void plusShouldBeNumberException() throws Exception {
        presenter.plus("", "5");
        verify(mockView, times(1)).showError(eq(R.string.invalid_number_format));
    }

    @Test
    public void loadUserInfoGitHubSuccess() throws Exception {
        UserInfoDao mockResult = jsonUtil.getJsonToMock(
                "user_info_success.json",
                UserInfoDao.class);

        Response<UserInfoDao> mockResponse = Response.success(mockResult);
        Observable<Response<UserInfoDao>> mockCall = Observable.just(mockResponse);
        when(gitHubApi.getUserInfo(anyString())).thenReturn(mockCall);
        presenter.loadUserInfo("pondthaitay");
        verify(mockView, times(1)).showProgressDialog();
        verify(disposable, times(1)).add(any(BaseSubscriber.class));

        TestObserver<Response<UserInfoDao>> testObserver =
                gitHubApi.getUserInfo(anyString()).test();
        testObserver.awaitTerminalEvent();
        testObserver.assertValue(response -> {
            verify(mockView, times(1)).hideProgressDialog();
            verify(mockView, times(1)).showResultUserInfoGitHubApi(eq(mockResult));
            assertThat(response, is(mockResponse));
            return true;
        });
    }

    @Test
    public void loadUserInfoGitHubError() throws Exception {
        Response<UserInfoDao> mockResponse = Response.error(500, responseBody);
        mockResponse.message();
        Observable<Response<UserInfoDao>> mockCall = Observable.just(mockResponse);
        when(gitHubApi.getUserInfo(anyString())).thenReturn(mockCall);
        presenter.loadUserInfo("");
        verify(mockView, times(1)).showProgressDialog();
        verify(disposable, times(1)).add(any(BaseSubscriber.class));

        TestObserver<Response<UserInfoDao>> testObserver =
                gitHubApi.getUserInfo(anyString()).test();
        testObserver.awaitTerminalEvent();
        testObserver.assertValue(response -> {
            verify(mockView, times(1)).hideProgressDialog();
            verify(mockView, times(1)).showError(eq(response.message()));
            assertThat(response.message(), is(mockResponse.message()));
            return true;
        });
    }


    @Test
    public void loadUserInfoGitHubUnAuthorized() throws Exception {
        Response<UserInfoDao> mockResponse = Response.error(401, responseBody);
        Observable<Response<UserInfoDao>> mockCall = Observable.just(mockResponse);
        when(gitHubApi.getUserInfo(anyString())).thenReturn(mockCall);
        presenter.loadUserInfo("pondthaitay");
        verify(mockView, times(1)).showProgressDialog();
        verify(disposable, times(1)).add(any(BaseSubscriber.class));

        TestObserver<Response<UserInfoDao>> testObserver =
                gitHubApi.getUserInfo(anyString()).test();
        testObserver.awaitTerminalEvent();
        testObserver.assertValue(response -> {
            verify(mockView, times(1)).hideProgressDialog();
            verify(mockView, times(1)).unAuthorizedApi();
            assertThat(response, is(mockResponse));
            return true;
        });
    }



    @Test
    public void testViewCreate() throws Exception {
        presenter.onViewCreate();
        verify(mockView).showMessage(R.string.view_create);
    }

    @Test
    public void testViewStart() throws Exception {
        presenter.onViewStart();
        verify(RxBus.get(), times(1)).register(presenter);
        verify(RxBus.get(), times(1)).post("tag_test", 555);
        verify(RxBus.get(), times(1)).post(any(TestBusEvent.class));
    }

    @Test
    public void testViewStop() throws Exception {
        presenter.onViewStop();
        verify(disposable, times(1)).clear();
        verify(RxBus.get(), times(1)).unregister(presenter);
    }

    @Test
    public void testViewDestroy() throws Exception {
        presenter.onViewDestroy();
        verify(mockView).showMessage(R.string.view_destroy);
    }

    @Test
    public void testSetResultBusTestBusEvent() {
        presenter.busTestObj(new TestBusEvent());
        verify(mockView, times(1)).showResultBusTestBusEvent(any(TestBusEvent.class));
    }

    @Test
    public void testSetResultBusTag() {
        presenter.busTestTag(anyInt());
        verify(mockView, times(1)).showResultBusTag(anyInt());
    }
}