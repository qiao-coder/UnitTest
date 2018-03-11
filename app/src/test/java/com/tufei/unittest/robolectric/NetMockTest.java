package com.tufei.unittest.robolectric;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tufei.unittest.net.GithubRepository;
import com.tufei.unittest.net.HttpService;
import com.tufei.unittest.net.NetConstants;
import com.tufei.unittest.net.UserBean;
import com.tufei.unittest.testutil.MockInterceptor;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author tufei
 * @date 2018/3/11.
 */

public class NetMockTest {

    private GithubRepository mGithubRepository;

    @Before
    public void setup() {
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
        HttpService httpService = retrofit.create(HttpService.class);

        mGithubRepository = new GithubRepository(httpService);
    }

    @Test
    public void getUser() {
        mGithubRepository.getUser()
                .test()
                .assertNoErrors()
                .assertComplete()
        .assertValue(new io.reactivex.functions.Predicate<UserBean>() {
            @Override
            public boolean test(UserBean userBean) throws Exception {
                return userBean.getLogin().equals("TuFeiBaBa");
            }
        });
    }
}
