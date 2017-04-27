package com.wisdomlanna.www.dagger2_mvp_example.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wisdomlanna.www.dagger2_mvp_example.BuildConfig;
import com.wisdomlanna.www.dagger2_mvp_example.Utils;
import com.wisdomlanna.www.dagger2_mvp_example.configuration.Config;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final int TIME_OUT = 60;
    public static String ENPOINT = BuildConfig.SEVER;

    @Provides
    @Singleton
    Cache provideOkHttpCache(Context context) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor(Utils utils) {
        return chain -> {
            Response response = chain.proceed(chain.request());
            if (utils.isNetworkAvailable()) {
                int maxAge = 60; // read from cache for 1 minute
                return response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        };
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpClient(Config config, Cache cache, Interceptor interceptor) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addNetworkInterceptor(interceptor);
        httpClient.cache(cache);

        httpClient.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool(0, TIME_OUT, TimeUnit.SECONDS));

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();

            if (config.userToken() != null)
                requestBuilder.header("Authorization", config.userToken());

            requestBuilder.header("device", "android");
            requestBuilder.header("appversion", config.version());
            requestBuilder.method(original.method(), original.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        return httpClient;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Config config, Gson gson, OkHttpClient.Builder httpClient) {
        addLoggingInterceptor(httpClient, config);
        return new Retrofit.Builder()
                .baseUrl(ENPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }

    private void addLoggingInterceptor(final OkHttpClient.Builder builder, final Config config) {
        if (!config.isDebug()) return;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);
    }
}