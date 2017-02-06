package com.wisdomlanna.www.dagger2_mvp_example.manager;

import com.wisdomlanna.www.dagger2_mvp_example.base.ConnectionCallback;

import java.net.HttpURLConnection;

import retrofit2.Response;
import timber.log.Timber;

public class DefaultSubscriber<T> extends io.reactivex.subscribers.DefaultSubscriber<Response<T>> {

    private ConnectionCallback callback;

    public DefaultSubscriber(ConnectionCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onNext(Response<T> response) {
        if (response.isSuccessful()) {
            callback.onSuccess(response.body());
        } else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            callback.onUnAuthorized();
        } else {
            callback.onGenericError(response.message());
        }
    }

    @Override
    public void onError(Throwable t) {
        Timber.d(t.getMessage());
        try {
            callback.onServerError(t.getMessage());
        } catch (Throwable e) {
            Timber.d(e.getMessage());
        }
    }

    @Override
    public void onComplete() {
        // TODO : something
    }
}