package com.tufei.unittest.robolectric;

import com.tufei.unittest.net.GithubRepository;
import com.tufei.unittest.net.HttpService;
import com.tufei.unittest.net.RetrofitFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * @author tufei
 * @date 2018/3/10.
 */

public class NetWithoutRobolectricTest {

    private GithubRepository mGithubRepository;

    @Before
    public void setup() {
        HttpService httpService = RetrofitFactory.createHttpService();
        mGithubRepository = new GithubRepository(httpService);
    }

    @Test
    public void getUser() {
        mGithubRepository.getUser()
                .test()
                .assertNoErrors();
    }
}
