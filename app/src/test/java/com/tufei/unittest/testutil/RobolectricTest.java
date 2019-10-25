package com.tufei.unittest.testutil;

import android.app.Application;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

/**
 * 基类。当需要使用robolectric测试时，继承该类即可。
 * 注意:
 * 1)这里不会再初始化我们自定义的Application。不用再担心自定义的Application里面的代码，
 * 会干扰测试。
 * 2)定义了{@link RobolectricRule}:开启了控制台日志输出。将RxJava的异步，变成同步。
 *
 * @author tufei
 * @date 2018/3/7.
 */
@RunWith(AndroidJUnit4.class)
@Config(application = Application.class)
@Ignore
public class RobolectricTest {
    @Rule
    public RobolectricRule mRobolectricRule = new RobolectricRule();
}
