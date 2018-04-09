package com.tufei.unittest.mockito;

import java.util.List;

/**
 * @author TuFei
 * @date 2018/4/8.
 */
public interface MainContract {
    interface View {
        void showPersons(List<Person> peoples);

        void showNotDataToast();
    }

    interface Presenter {
        void attachView(View view);

        void loadData();
    }
}
