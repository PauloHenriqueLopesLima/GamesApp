package com.example.gamesapp.service;

import com.example.gamesapp.service.api.GamesApi;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;
    private static final String BASE_URL = "https://www.giantbomb.com/api/";

    private Retrofit getRetrofit() {
        if (retrofit == null){
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            clientBuilder.connectTimeout(30, TimeUnit.SECONDS);
            clientBuilder.readTimeout(30, TimeUnit.SECONDS);
            clientBuilder.writeTimeout(30, TimeUnit.SECONDS);
            clientBuilder.addNetworkInterceptor(new StethoInterceptor());
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(clientBuilder.build())
                    .build();
        }
        return retrofit;
    }

    public GamesApi getGamesApi () {
        return getRetrofit().create(GamesApi.class);
    }


}
