package com.tufei.unittest.testutil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tufei.unittest.net.HttpService;
import com.tufei.unittest.net.NetConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author TuFei
 * @date 18-8-28.
 */
public class RetrofitMockFactory {
    public static HttpService createHttpService() {
        MockInterceptor mockInterceptor = new MockInterceptor("user.json");
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(mockInterceptor)
                .connectTimeout(NetConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(NetConstants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(HttpService.class);
    }
}
