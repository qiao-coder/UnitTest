package com.tufei.unittest.robolectric;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.tufei.unittest.LoginActivity;
import com.tufei.unittest.MainActivity;
import com.tufei.unittest.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

/**
 * @author tufei
 * @date 2018/1/22.
 */
@RunWith(RobolectricTestRunner.class)
public class AndroidTest {

    private TextView mTvHello;
    private Button mBtnJump;
    private MainActivity mMainActivity;

    @Before
    public void setup() {
        ShadowLog.stream = System.out;

        //Activity初始化
        //模拟的MainActivity已经执行了onCreate->onStart->onResume
        mMainActivity = Robolectric.setupActivity(MainActivity.class);

        mTvHello = mMainActivity.findViewById(R.id.tv_hello);
        mBtnJump = mMainActivity.findViewById(R.id.btn_jump);
    }

    @Test
    public void testRobolectric() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testUI() {
        String text = mTvHello.getText().toString();
        assertEquals("Hello,tufei!", text);
    }

    @Test
    public void testStartActivity() {
        //模拟用户点击
        mBtnJump.performClick();

        //因为robolectric是一个unit testing framework，LoginActivity不会真的启动
        //但我们可以检查MainActivity是否激活正确的intent
        Intent expectedIntent = new Intent(mMainActivity, LoginActivity.class);

        //ShadowApplication.getInstance()等同于shadowOf(RuntimeEnvironment.application);
        //RuntimeEnvironment.application是robolectric模拟android环境生成的Application实例
        //Intent actual = ShadowApplication.getInstance().getNextStartedActivity();

        ShadowActivity shadowActivity = shadowOf(mMainActivity);
        Intent actual = shadowActivity.getNextStartedActivity();

        //错误的比较方法
        //assertEquals(expectedIntent, actual);
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }
}
