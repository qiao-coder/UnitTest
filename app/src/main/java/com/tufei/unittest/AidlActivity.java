package com.tufei.unittest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * @author tufei
 * @date 2018/1/24.
 */

public class AidlActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnBind;
    private Button mBtnUnbind;
    private Button mBtnKill;
    private Button mBtnGreet;
    private IRemoteService mService;
    private boolean mIsBound;
    private RemoteServiceConn mConn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        mBtnBind = findViewById(R.id.btn_bind);
        mBtnUnbind = findViewById(R.id.btn_unbind);
        mBtnKill = findViewById(R.id.btn_kill);
        mBtnGreet = findViewById(R.id.btn_greet);

        mBtnBind.setOnClickListener(this);
        mBtnUnbind.setOnClickListener(this);
        mBtnKill.setOnClickListener(this);
        mBtnGreet.setOnClickListener(this);

        mBtnKill.setEnabled(false);

        mConn = new RemoteServiceConn();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind:
                Intent intent = new Intent(this, RemoteService.class);
                intent.setAction(IRemoteService.class.getName());
                bindService(intent, mConn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                if (!mIsBound) {
                    return;
                }
                unbindService(mConn);
                mBtnKill.setEnabled(false);
                mIsBound = false;
                break;
            case R.id.btn_kill:
                if (mService == null) {
                    return;
                }
                try {
                    int pid = mService.getPid();
                    Process.killProcess(pid);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_greet:
                if (mService == null) {
                    return;
                }
                try {
                    mService.sayHello("I'm Tufei!");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    class RemoteServiceConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IRemoteService.Stub.asInterface(service);
            mBtnKill.setEnabled(true);
        }


        /**
         * //如果服务是被手动杀死，或者被系统杀死，才会走
         *
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            mBtnKill.setEnabled(false);
        }
    }
}
