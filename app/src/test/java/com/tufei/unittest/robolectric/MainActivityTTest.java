package com.tufei.unittest.robolectric;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.tufei.unittest.ComponentHolder;
import com.tufei.unittest.LoginActivity;
import com.tufei.unittest.MainActivity;
import com.tufei.unittest.R;
import com.tufei.unittest.di.AppComponent;
import com.tufei.unittest.di.DaggerAppComponent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.android.fakes.RoboMonitoringInstrumentation;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowActivityThread;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowContextImpl;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author TuFei
 * @date 2019/5/21.
 */
@RunWith(AndroidJUnit4.class)
@Config(application = Application.class)
public class MainActivityTTest {
    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class, true, false);

    @Before
    public void setup() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .build();
        ComponentHolder.setAppComponent(appComponent);

        intentsTestRule.launchActivity(new Intent(ApplicationProvider.getApplicationContext(), MainActivity.class));
    }

    @Test
    public void test() {
//        ActivityScenario.launch(MainActivity.class);
        onView(ViewMatchers.withId(R.id.tv_hello)).check(matches(withText("Hello,tufei!")));
    }

    @Test
    public void testClickButton() {
        onView(withId(R.id.btn_jump)).perform(ViewActions.click());
        intended(IntentMatchers.hasComponent(LoginActivity.class.getName()));

        //在robolectric里，无法真的跳转到LoginActivity
        onView(withId(R.id.tv_login)).check(doesNotExist());
    }
}