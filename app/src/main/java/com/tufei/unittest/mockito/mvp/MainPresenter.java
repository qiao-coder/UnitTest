package com.tufei.unittest.mockito.mvp;

import java.util.List;

/**
 * @author TuFei
 * @date 2018/4/8.
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private TestRepository testRepository;

    public MainPresenter(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        getDataAndHandle();
    }

    private void getDataAndHandle() {
        //省略一大堆可能的处理逻辑......
        testRepository.getData(new GetDataCallback() {
            @Override
            public void onSuccess(List<Person> peoples) {
                //省略一大堆可能的处理逻辑......
                if (view != null) {
                    view.showPersons(peoples);
                }
            }

            @Override
            public void onError() {
                //省略一大堆可能的处理逻辑......
                if (view != null) {
                    view.showNotDataToast();
                }
            }
        });
    }
}
