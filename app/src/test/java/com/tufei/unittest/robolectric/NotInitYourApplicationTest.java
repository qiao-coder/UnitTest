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
 * 虽然博文中有提到，日志输出设置，在我们自定义的Application里面，是不起效果的。
 * 但如果我们想看下面这行配置，起不起作用，只要去掉@Config(application = Application.class)
 * 这行配置，然后看在{@link App}打断点，看看onCreate方法会不会执行。
 *
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
