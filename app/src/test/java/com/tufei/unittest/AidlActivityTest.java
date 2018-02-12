package com.tufei.unittest;

import android.content.Intent;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowLog;

import static junit.framework.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

/**
 * @author tufei
 * @date 2018/1/24.
 */
@RunWith(RobolectricTestRunner.class)
public class AidlActivityTest {

    private AidlActivity mAidlActivity;
    private Button mBtnBind;
    private Button mBtnUnbind;
    private Button mBtnKill;
    private Button mBtnGreet;

    @Before
    public void setup() {
        ShadowLog.stream = System.out;

        mAidlActivity = Robolectric.setupActivity(AidlActivity.class);
        mBtnBind = mAidlActivity.findViewById(R.id.btn_bind);
        mBtnUnbind = mAidlActivity.findViewById(R.id.btn_unbind);
        mBtnKill = mAidlActivity.findViewById(R.id.btn_kill);
        mBtnGreet = mAidlActivity.findViewById(R.id.btn_greet);
    }

    @Test
    public void testStartServiceInOtherProcess(){
        mBtnBind.performClick();

        ShadowActivity shadowActivity = shadowOf(mAidlActivity);
        Intent actual = shadowActivity.getNextStartedService();

        Intent expectedIntent = new Intent(mAidlActivity, RemoteService.class);
        expectedIntent.setAction(IRemoteService.class.getName());

        assertEquals(expectedIntent.getComponent(),actual.getComponent());
        assertEquals(expectedIntent.getAction(),actual.getAction());

    }
}