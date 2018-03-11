package com.tufei.unittest.net;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author tufei
 * @date 2017/9/12
 */
public interface HttpService {
    @GET("users/{username}")
    Single<UserBean> getUser(@Path("username") String username);
}
