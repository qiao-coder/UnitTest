package com.tufei.unittest.testutil;

import android.app.Application;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

/**
 * @author TuFei
 * @date 2018/8/5.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, sdk = 23, application = Application.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*", "javax.net.ssl.*", "javax.xml.*", "org.xml.*", "org.w3c.*"})
@Ignore
public class PowerRobolectricTest {
    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Rule
    public RobolectricRule mRobolectricRule = new RobolectricRule();
}
