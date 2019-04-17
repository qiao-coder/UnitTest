package com.tufei.unittest;

import com.tufei.unittest.di.AppComponent;

/**
 * @author TuFei
 * @date 19-4-8.
 */
public class ComponentHolder {

    private static AppComponent appComponent;

    public static void setAppComponent(AppComponent appComponent) {
        ComponentHolder.appComponent = appComponent;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
