package com.tufei.unittest.net;

import com.tufei.unittest.net.GithubRepository;
import com.tufei.unittest.net.HttpService;
import com.tufei.unittest.net.NetConstants;
import com.tufei.unittest.net.RetrofitFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.Okio;
import retrofit2.HttpException;

/**
 * @author tufei
 * @date 2018/3/11.
 */

public class NetworkMockTest {

    private GithubRepository mGithubRepository;
    @Rule
    public MockWebServer server = new MockWebServer();

    @Before
    public void setup() throws IOException {
        NetConstants.BASE_URL = server.url("/").toString();

        HttpService httpService = RetrofitFactory.createHttpService();
        mGithubRepository = new GithubRepository(httpService);
    }

    @Test
    public void getUserOnSuccess() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/user.json");
        String json = Okio.buffer(Okio.source(inputStream)).readString(StandardCharsets.UTF_8);
        server.enqueue(new MockResponse().setBody(json));
        mGithubRepository.getUser()
                .test()
                .assertNoErrors()
                .assertComplete()
                .assertValue(userBean ->
                        userBean.getLogin().equals("TuFei"));
    }

    @Test
    public void getUserOnError() {
        server.enqueue(new MockResponse().setResponseCode(404));
        mGithubRepository.getUser()
                .test()
                .assertError(HttpException.class)
                .assertErrorMessage("HTTP 404 Client Error");
    }

    @Test
    public void getUserOnConnectTimeOut() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/user.json");
        String json = Okio.buffer(Okio.source(inputStream)).readString(StandardCharsets.UTF_8);
        server.enqueue(new MockResponse()
                .setBody(json)
                .setResponseCode(504)
                .throttleBody(1024, 10, TimeUnit.SECONDS));
        mGithubRepository.getUser()
                .test()
                .assertNotComplete()
                .assertError(SocketTimeoutException.class);
    }

}
