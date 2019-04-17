package com.tufei.unittest;

import android.util.Log;

import javax.inject.Inject;

/**
 * @author TuFei
 * @date 19-4-8.
 */
public class MainPresenter {
    private static final String TAG = "MainPresenter";
    @Inject
    public MainPresenter() {

    }

    public void println(){
        Log.d(TAG, "println: Hello world!");
    }
}
