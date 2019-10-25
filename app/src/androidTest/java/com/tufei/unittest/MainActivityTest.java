package com.tufei.unittest;

import android.content.Context;
import android.util.Log;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.not;

/**
 * @author TuFei
 * @date 2019/5/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
//    @Rule
//    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);

//    @Rule
//    public ActivityScenarioRule<MainActivity> activityActivityScenario = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup() {
        Log.e("a", "aa");
    }

    @Test
    public void testViewShow() {
        //下面三句并不是断言  只是当多个图层的控件id名重复时，可以利用条件筛选出特定的控件
        onView(allOf(withId(R.id.tv_hello), withText("Hello,tufei!")));
//        onView(allOf(withId(R.id.tv_describe), withText("...")));
        onView(allOf(withId(R.id.tv_hello), not(withText("..."))));

        onView(withId(R.id.tv_hello)).check(matches(withText("Hello,tufei!")));
//        onView(withId(R.id.tv_describe)).check(matches(withText("...")));

        onView(withText("Hello,tufei!")).check(matches(isDisplayed()));
//        onView(withText("...")).check(matches(isDisplayed()));
    }

    @Test
    public void testClickButton() {
        onView(withId(R.id.btn_jump)).perform(ViewActions.click());
        intended(IntentMatchers.hasComponent(LoginActivity.class.getName()));
        onView(withId(R.id.tv_login)).check(matches(withText("haha")));
    }
}