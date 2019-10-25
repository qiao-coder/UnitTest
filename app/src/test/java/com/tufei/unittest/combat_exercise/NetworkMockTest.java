package com.tufei.unittest.combat_exercise;

import androidx.annotation.VisibleForTesting;

import com.tufei.unittest.net.GithubRepository;
import com.tufei.unittest.net.HttpService;
import com.tufei.unittest.testutil.RetrofitMockFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * @author tufei
 * @date 2018/3/11.
 */

public class NetworkMockTest {

    private GithubRepository mGithubRepository;

    @Before
    public void setup() {
        HttpService httpService = RetrofitMockFactory.createHttpService();
        mGithubRepository = new GithubRepository(httpService);
    }

    @Test
    @VisibleForTesting
    public void getUser() {
        mGithubRepository.getUser()
                .test()
                .assertNoErrors()
                .assertComplete()
                .assertValue(userBean ->
                        userBean.getLogin().equals("TuFei"));
    }
}
