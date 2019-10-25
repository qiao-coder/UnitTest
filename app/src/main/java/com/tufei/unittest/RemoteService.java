package com.tufei.unittest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import android.util.Log;

/**
 * @author tufei
 * @date 2018/1/24.
 */

public class RemoteService extends Service {
    private static final String TAG = "RemoteService";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        if (IRemoteService.class.getName().equals(action)) {
            return new RemoteStub();
        }
        return null;
    }

    class RemoteStub extends IRemoteService.Stub {

        @Override
        public void sayHello(String msg) {

        }

        @Override
        public int getPid() {
            return android.os.Process.myPid();
        }
    }
}
