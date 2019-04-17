package com.tufei.unittest;

import android.app.Application;

import com.tufei.unittest.di.AppComponent;
import com.tufei.unittest.di.DaggerAppComponent;

/**
 * @author tufei
 * @date 2018/3/10.
 */

public class App extends Application {
    private static final String TAG = "App";
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .build();
        ComponentHolder.setAppComponent(appComponent);
    }
}
