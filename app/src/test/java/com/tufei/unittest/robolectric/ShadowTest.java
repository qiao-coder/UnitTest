package com.tufei.unittest.robolectric;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

import static org.junit.Assert.assertEquals;

/**
 * @author TuFei
 * @date 2019/10/25.
 */
@RunWith(RobolectricTestRunner.class)
@Config(shadows = {ShadowTest.ShadowPerson.class}, manifest = Config.NONE,sdk = 28)
public class ShadowTest {
    public static class Person {
        public String sayHello() {
            return "I'm myself!";
        }
    }

    @Implements(Person.class)
    public static class ShadowPerson {
        @Implementation
        public String sayHello() {
            return "I'm shadow!";
        }
    }

    @Test
    public void sayHello() {
        Person person = new Person();
        assertEquals("I'm shadow!", person.sayHello());
    }
}
