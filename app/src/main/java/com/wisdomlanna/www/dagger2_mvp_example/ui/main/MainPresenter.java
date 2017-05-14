package com.wisdomlanna.www.dagger2_mvp_example.ui.main;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.wisdomlanna.www.dagger2_mvp_example.R;
import com.wisdomlanna.www.dagger2_mvp_example.api.BaseSubscriber;
import com.wisdomlanna.www.dagger2_mvp_example.api.dao.UserInfoDao;
import com.wisdomlanna.www.dagger2_mvp_example.api.service.GitHubApi;
import com.wisdomlanna.www.dagger2_mvp_example.manager.Calculator;
import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BasePresenter;
import com.wisdomlanna.www.dagger2_mvp_example.ui.event.TestBusEvent;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainInterface.View> implements MainInterface.Presenter,
        BaseSubscriber.NetworkCallback {

    private GitHubApi gitHubApi;
    private CompositeDisposable disposables;
    private Calculator calculator;

    @Inject
    public MainPresenter(GitHubApi gitHubApi, CompositeDisposable disposables, Calculator calculator) {
        super();
        this.gitHubApi = gitHubApi;
        this.disposables = disposables;
        this.calculator = calculator;
    }

    @Override
    public void plus(String x, String y) {
        if (getView() != null) {
            try {
                getView().showResultPlus(calculator.plus(Integer.parseInt(x), Integer.parseInt(y)));
            } catch (NumberFormatException e) {
                getView().showError(R.string.invalid_number_format);
            }
        }
    }

    @Override
    public void loadUserInfo(String username) {
        if (getView() != null) {
            getView().showProgressDialog();
            disposables.add(gitHubApi.getUserInfo(username)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new BaseSubscriber<>(this)));
        }
    }

    @Override
    public <T> void onSuccess(T t) {
        if (getView() != null) {
            getView().hideProgressDialog();
            getView().showResultUserInfoGitHubApi((UserInfoDao) t);
        }
    }

    @Override
    public void onFailure(String message) {
        if (getView() != null) {
            getView().hideProgressDialog();
            getView().showError(message);
        }
    }

    @Override
    public void onViewCreate() {
        if (getView() != null) getView().showMessage(R.string.view_create);
    }

    @Override
    public void onViewDestroy() {
        if (getView() != null) getView().showMessage(R.string.view_destroy);
    }

    @Override
    public void onViewStart() {
        if (getView() != null) {
            RxBus.get().register(this);
            TestBusEvent event = new TestBusEvent();
            event.setName("Jedsada Tiwongvorakul");
            event.setAge(22);
            RxBus.get().post("tag_test", 555);
            RxBus.get().post(event);
        }
    }

    @Override
    public void onViewStop() {
        if (getView() != null) {
            disposables.clear();
            RxBus.get().unregister(this);
        }
    }

    @Subscribe(thread = EventThread.MAIN_THREAD, tags = {@Tag("tag_test")})
    public void busTestTag(Integer result) {
        if (getView() != null) getView().showResultBusTag(result);
    }

    @Subscribe(thread = EventThread.MAIN_THREAD)
    public void busTestObj(TestBusEvent event) {
        if (getView() != null) getView().showResultBusTestBusEvent(event);
    }
}