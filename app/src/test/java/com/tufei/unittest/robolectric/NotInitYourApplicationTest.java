package com.tufei.unittest.robolectric;

import android.app.Application;

import com.tufei.unittest.App;
import com.tufei.unittest.testutil.RobolectricRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * @author tufei
 * @date 2018/3/10.
 */
@RunWith(RobolectricTestRunner.class)
@Config(application = Application.class)
public class NotInitYourApplicationTest {

    @Rule
    public RobolectricRule mRobolectricRule = new RobolectricRule();

    @Test
    public void test() {

    }
}
