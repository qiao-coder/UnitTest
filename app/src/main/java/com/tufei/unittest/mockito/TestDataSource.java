package com.tufei.unittest.mockito;

import java.util.List;

/**
 * @author TuFei
 * @date 2018/4/8.
 */
public interface TestDataSource {
    void getData(GetDataCallback getDataCallback);

    interface GetDataCallback{
        void onSuccess(List<Person> data);

        void onError();
    }
}
