package com.tufei.unittest.mockito.mvp;

import java.util.List;

/**
 * @author TuFei
 * @date 2018/4/12.
 */
public interface GetDataCallback {
    void onSuccess(List<Person> data);

    void onError();
}
