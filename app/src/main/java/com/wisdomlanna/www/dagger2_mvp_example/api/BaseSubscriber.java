package com.wisdomlanna.www.dagger2_mvp_example.api;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseInterface;

import java.net.HttpURLConnection;

import io.reactivex.observers.DefaultObserver;
import retrofit2.Response;
import timber.log.Timber;

public class BaseSubscriber<T> extends DefaultObserver<Response<T>> {

    private NetworkCallback callback;

    public interface NetworkCallback extends BaseInterface.UnAuthorizedCallback {
        <T> void onSuccess(T result);

        void onFailure(String message);
    }

    public BaseSubscriber(NetworkCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onNext(Response<T> response) {
        if (response.isSuccessful()) {
            callback.onSuccess(response.body());
        } else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            callback.onUnAuthorized();
        } else {
            callback.onFailure(response.message());
        }
    }

    @Override
    public void onError(Throwable t) {
        Timber.d(t.getMessage());
        callback.onFailure(t.getMessage());
    }

    @Override
    public void onComplete() {
        /* TODO : somethings on complete */
    }
}