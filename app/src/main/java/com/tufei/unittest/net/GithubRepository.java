package com.tufei.unittest.net;

import io.reactivex.Single;

/**
 * @author tufei
 * @date 2018/3/10.
 */

public class GithubRepository {

    private final HttpService mHttpService;

    public GithubRepository(HttpService httpService) {
        mHttpService = httpService;
    }

    public Single<UserBean> getUser() {
        return mHttpService.getUser("TuFeiBaBa");
    }
}
