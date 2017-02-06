package com.wisdomlanna.www.dagger2_mvp_example.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wisdomlanna.www.dagger2_mvp_example.api.GitHubApi;
import com.wisdomlanna.www.dagger2_mvp_example.api.GitHubApiDataSource;
import com.wisdomlanna.www.dagger2_mvp_example.api.GitHubDataSource;
import com.wisdomlanna.www.dagger2_mvp_example.configuration.Config;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final int TIME_OUT = 60;
    private static final String BASE_URL = "https://api.github.com/";

    @Singleton
    @Provides
    Retrofit provideRetrofit(Config config) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder();
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

        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        addLoggingInterceptor(httpClient, config);

        return builder.baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }


    private void addLoggingInterceptor(final OkHttpClient.Builder builder, final Config config) {
        if (!config.isDebug()) {
            return;
        }

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);
    }

    @Singleton
    @Provides
    GitHubApi provideService(Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }

    @Singleton
    @Provides
    GitHubDataSource provideDataSource(GitHubApi gitHubApi) {
        return new GitHubApiDataSource(gitHubApi);
    }
}