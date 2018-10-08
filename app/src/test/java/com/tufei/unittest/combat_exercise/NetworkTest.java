package com.tufei.unittest.combat_exercise;

import com.tufei.unittest.net.GithubRepository;
import com.tufei.unittest.net.HttpService;
import com.tufei.unittest.net.RetrofitFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * @author tufei
 * @date 2018/3/10.
 */

public class NetworkTest {

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
