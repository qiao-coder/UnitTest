package com.tufei.unittest.powermock;

import com.tufei.unittest.PowerMockRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @author TuFei
 * @date 2018/3/22.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PowerMockSample.class)
public class PowerMockSampleTest {

    private PowerMockSample powerMockSample;

    @Before
    public void setup() {
        powerMockSample = new PowerMockSample();
    }

    @Test
    public void mockPrivateInt() {
        int newValue = 2;
        //mock非静态变量，第一个参数传的是实例。
        //不管mock的是private变量还是public变量，第二个参数传的都是变量名。
        Whitebox.setInternalState(powerMockSample, "privateInt", newValue);
        assertEquals(newValue, getFieldValue(PowerMockSample.class, "privateInt", powerMockSample));
        assertEquals(newValue, powerMockSample.getPrivateInt());
    }

    @Test
    public void mockPrivateString() {
        String newValue = "mockPrivateString";
        //mock非静态变量，第一个参数传的是实例
        Whitebox.setInternalState(powerMockSample, "privateString", newValue);
        assertEquals(newValue, getFieldValue(PowerMockSample.class, "privateString", powerMockSample));
        assertEquals(newValue, powerMockSample.getPrivateString());
    }

    @Test
    public void mockPrivateInteger() {
        Integer newValue = 2;
        //mock非静态变量，第一个参数传的是实例
        Whitebox.setInternalState(powerMockSample, "privateInteger", newValue);
        assertEquals(newValue, getFieldValue(PowerMockSample.class, "privateInteger", powerMockSample));
        assertEquals(newValue, getFieldValue(PowerMockSample.class, "privateInteger", powerMockSample));
    }

    @Test
    public void mockPrivateFinalInt() {
        int newValue = 2;
        Whitebox.setInternalState(powerMockSample, "privateFinalInt", newValue);
        assertEquals(newValue, getFieldValue(PowerMockSample.class, "privateFinalInt", powerMockSample));
        //注意，这里并不相等
        assertNotEquals(newValue, powerMockSample.getPrivateFinalInt());
    }

    @Test
    public void mockPrivateFinalString() {
        String newValue = "mockPrivateFinalString";
        Whitebox.setInternalState(powerMockSample, "privateFinalString", newValue);
        assertEquals(newValue, getFieldValue(PowerMockSample.class, "privateFinalString", powerMockSample));
        //注意，这里并不相等
        assertNotEquals(newValue, powerMockSample.getPrivateFinalString());
    }

    @Test
    public void mockPrivateFinalInteger() {
        Integer newValue = 2;
        Whitebox.setInternalState(powerMockSample, "privateFinalInteger", newValue);
        assertEquals(newValue, powerMockSample.getPrivateFinalInteger());
        assertEquals(newValue, powerMockSample.getPrivateFinalInteger());
    }


    @Test
    public void mockPrivateStaticInt() {
        int newValue = 2;
        //mock静态变量，第一个参数传的是class
        Whitebox.setInternalState(PowerMockSample.class, "privateStaticInt", newValue);
        assertEquals(newValue, getStaticFieldValue(PowerMockSample.class, "privateStaticInt"));
        assertEquals(newValue, PowerMockSample.getPrivateStaticInt());
    }

    @Test
    public void mockPrivateStaticString() {
        String newValue = "mockPrivateStaticString";
        //mock静态变量，第一个参数传的是class
        Whitebox.setInternalState(PowerMockSample.class, "privateStaticString", newValue);
        assertEquals(newValue, getStaticFieldValue(PowerMockSample.class, "privateStaticString"));
        assertEquals(newValue, PowerMockSample.getPrivateStaticString());
    }

    @Test
    public void mockPrivateStaticInteger() {
        Integer newValue = 2;
        //mock静态变量，第一个参数传的是class
        Whitebox.setInternalState(PowerMockSample.class, "privateStaticInteger", newValue);
        assertEquals(newValue, getStaticFieldValue(PowerMockSample.class, "privateStaticInteger"));
        assertEquals(newValue, PowerMockSample.getPrivateStaticInteger());
    }

    @Test
    public void mockPrivateStaticFinalInt() {
        int newValue = 2;
        //mock静态变量，第一个参数传的是class
        Whitebox.setInternalState(PowerMockSample.class, "privateStaticFinalInt", newValue);
        assertEquals(newValue, getStaticFieldValue(PowerMockSample.class, "privateStaticFinalInt"));
        //注意，这里并不相等
        assertNotEquals(newValue, PowerMockSample.getPrivateStaticFinalInt());
    }

    @Test
    public void mockPrivateStaticFinalString() {
        String newValue = "mockPrivateStaticFinalString";
        //mock静态变量，第一个参数传的是class
        Whitebox.setInternalState(PowerMockSample.class, "privateStaticFinalString", newValue);
        assertEquals(newValue, getStaticFieldValue(PowerMockSample.class, "privateStaticFinalString"));
        //注意，这里并不相等
        assertNotEquals(newValue, PowerMockSample.getPrivateStaticFinalString());
    }

    @Test
    public void mockPrivateStaticFinalInteger() {
        int newValue = 2;
        //mock静态变量，第一个参数传的是class
        Whitebox.setInternalState(PowerMockSample.class, "privateStaticFinalInteger", newValue);
        assertEquals(newValue, getStaticFieldValue(PowerMockSample.class, "privateStaticFinalInteger"));
        assertEquals(newValue, PowerMockSample.getPrivateStaticFinalInteger());
    }

    @Test
    public void mockPublicInt() {
        int newValue = 2;
        //mock非静态变量，第一个参数传的是实例
        Whitebox.setInternalState(powerMockSample, "publicInt", newValue);
        assertEquals(newValue, getFieldValue(PowerMockSample.class, "publicInt", powerMockSample));
        assertEquals(newValue, powerMockSample.publicInt);
    }

    @Test
    public void mockPublicString() {
        String newValue = "publicString";
        //mock非静态变量，第一个参数传的是实例
        Whitebox.setInternalState(powerMockSample, "publicString", newValue);
        assertEquals(newValue, getFieldValue(PowerMockSample.class, "publicString", powerMockSample));
        assertEquals(newValue, powerMockSample.publicString);
    }

    @Test
    public void mockPublicInteger() {
        Integer newValue = 2;
        //mock非静态变量，第一个参数传的是实例
        Whitebox.setInternalState(powerMockSample, "publicInteger", newValue);
        assertEquals(newValue, getFieldValue(PowerMockSample.class, "publicInteger", powerMockSample));
        assertEquals(newValue, powerMockSample.publicInteger);
    }

    @Test
    public void mockPublicStaticInt() {
        int newValue = 2;
        Whitebox.setInternalState(PowerMockSample.class, "publicStaticInt", newValue);
        assertEquals(newValue, getStaticFieldValue(PowerMockSample.class, "publicStaticInt"));
        assertEquals(newValue, PowerMockSample.publicStaticInt);
    }

    @Test
    public void mockPublicStaticString() {
        String newValue = "publicStaticString";
        Whitebox.setInternalState(PowerMockSample.class, "publicStaticString", newValue);
        assertEquals(newValue, getStaticFieldValue(PowerMockSample.class, "publicStaticString"));
        assertEquals(newValue, PowerMockSample.publicStaticString);
    }

    @Test
    public void mockPublicStaticInteger() {
        Integer newValue = 2;
        Whitebox.setInternalState(PowerMockSample.class, "publicStaticInteger", newValue);
        assertEquals(newValue, getStaticFieldValue(PowerMockSample.class, "publicStaticInteger"));
        assertEquals(newValue, PowerMockSample.publicStaticInteger);
    }

    @Test
    public void mockPublicFinalInt() {
        int newValue = 2;
        Whitebox.setInternalState(powerMockSample, "publicFinalInt", newValue);
        assertEquals(newValue, getFieldValue(PowerMockSample.class, "publicFinalInt", powerMockSample));
        //注意，这里并不相等
        assertNotEquals(newValue, powerMockSample.publicFinalInt);
    }

    @Test
    public void mockPublicFinalString() {
        String newValue = "newPublicFinalString";
        Whitebox.setInternalState(powerMockSample, "publicFinalString", newValue);
        assertEquals(newValue, getFieldValue(PowerMockSample.class, "publicFinalString", powerMockSample));
        //注意，这里并不相等
        assertNotEquals(newValue, powerMockSample.publicFinalString);
    }

    @Test
    public void mockPublicFinalInteger() {
        Integer newValue = 2;
        Whitebox.setInternalState(powerMockSample, "publicFinalInteger", newValue);
        assertEquals(newValue, getFieldValue(PowerMockSample.class, "publicFinalInteger", powerMockSample));
        assertEquals(newValue, powerMockSample.publicFinalInteger);
    }

    @Test
    public void mockPublicStaticFinalInt() {
        int newValue = 2;
        Whitebox.setInternalState(PowerMockSample.class, "publicStaticFinalInt", newValue);
        assertEquals(newValue, getStaticFieldValue(PowerMockSample.class, "publicStaticFinalInt"));
        //注意，这里并不相等
        assertNotEquals(newValue, PowerMockSample.publicStaticFinalInt);
    }

    @Test
    public void mockPublicStaticFinalString() {
        String newValue = "mockPublicStaticFinalString";
        //第一个参数，传的是class
        Whitebox.setInternalState(PowerMockSample.class, "publicStaticFinalString", newValue);
        assertEquals(newValue, getStaticFieldValue(PowerMockSample.class, "publicStaticFinalString"));
        //注意，这里并不相等
        assertNotEquals(newValue, PowerMockSample.publicStaticFinalString);
    }

    @Test
    public void mockPublicStaticFinalInteger() {
        Integer newValue = 2;
        //第一个参数，传的是class
        Whitebox.setInternalState(PowerMockSample.class, "publicStaticFinalInteger", newValue);
        assertEquals(newValue, getStaticFieldValue(PowerMockSample.class, "publicStaticFinalInteger"));
        assertEquals(newValue, PowerMockSample.publicStaticFinalInteger);
    }

    @Test
    public void spyObject_mockPrivateMethodReturnString() throws Exception {
        String oleValue = "privateMethodReturnString";
        String newValue = "mockPrivateMethodReturnString";

        //不行，该方法是没有返回值的。它其实是用来mock静态方法的。它被称之为spyStatic更适合。
        //PowerMockito.spy(PowerMockSample.class);
        powerMockSample = PowerMockito.spy(powerMockSample);
        String actual_1 = Whitebox.invokeMethod(powerMockSample, "privateMethodReturnString");
        assertEquals(oleValue, actual_1);
        //不妥。在返回newValue之前，会先走真实逻辑。
        //when(powerMockSample, "privateMethodReturnString").thenReturn(newValue);
        doReturn(newValue).when(powerMockSample, "privateMethodReturnString");
        String actual_2 = Whitebox.invokeMethod(powerMockSample, "privateMethodReturnString");
        assertEquals(newValue, actual_2);
    }

    @Test
    public void spyObject_mockPrivateMethodNoReturnThrowException() throws Exception {
        powerMockSample = PowerMockito.spy(powerMockSample);

        try {
            Whitebox.invokeMethod(powerMockSample, "privateMethodNoReturnThrowException");
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }

        //传的是mock过的实例
        doNothing().when(powerMockSample, "privateMethodNoReturnThrowException");

        Whitebox.invokeMethod(powerMockSample, "privateMethodNoReturnThrowException");
    }

    @Test
    public void spyObject_mockPrivateMethodCalculateThrowException() throws Exception {
        int expected = 10;

        powerMockSample = PowerMockito.spy(powerMockSample);

        //不要使用when(...).thenReturn(...)。会调用你想要mock的方法的真实逻辑。然后才返回mock的结果。
        //doReturn方法的注释，也提供了相关解释和例子。
//        when(powerMockSample, "privateMethodCalculateThrowException", isA(int.class), isA(int.class)).thenReturn(expected);
        doReturn(expected).when(powerMockSample, "privateMethodCalculateThrowException", isA(int.class), isA(int.class));
        int actual = Whitebox.invokeMethod(powerMockSample, "privateMethodCalculateThrowException", 1, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void mockClass_mockPrivateMethodCalculateThrowException() throws Exception {
        int expected = 10;
        powerMockSample = PowerMockito.mock(PowerMockSample.class);
        //两者均可
//        when(powerMockSample,"privateMethodCalculateThrowException", isA(int.class), isA(int.class)).thenReturn(expected);
        doReturn(expected).when(powerMockSample, "privateMethodCalculateThrowException", isA(int.class), isA(int.class));
        int actual = Whitebox.invokeMethod(powerMockSample, "privateMethodCalculateThrowException", 1, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void spyObject_mockPrivateMethodCalculate() throws Exception {
        int expected = 10;
        powerMockSample = PowerMockito.spy(powerMockSample);
        int actual_1 = Whitebox.invokeMethod(powerMockSample, "privateMethodCalculate", 1, 2);
        assertEquals(3, actual_1);
        doReturn(expected).when(powerMockSample, "privateMethodCalculate", isA(int.class), isA(int.class));
        int actual_2 = Whitebox.invokeMethod(powerMockSample, "privateMethodCalculate", 1, 2);
        assertEquals(expected, actual_2);
    }

    @Test
    public void spyClass_mockPrivateStaticMethodReturnString() throws Exception {
        String oldValue = "privateStaticMethodReturnString";
        String newValue = "mockPrivateStaticMethodReturnString";

        PowerMockito.spy(PowerMockSample.class);
        String actual_1 = Whitebox.invokeMethod(powerMockSample, "privateStaticMethodReturnString");
        assertEquals(oldValue, actual_1);

        //传的是mock过的class
        doReturn(newValue).when(PowerMockSample.class, "privateStaticMethodReturnString");
        String actual_2 = Whitebox.invokeMethod(powerMockSample, "privateStaticMethodReturnString");
        assertEquals(newValue, actual_2);
    }

    @Test
    public void spyClass_mockPrivateStaticMethodCalculateThrowException() throws Exception {
        int expected = 10;
        PowerMockito.spy(PowerMockSample.class);
        //不可行，还是会走实践逻辑
//        when(PowerMockSample.class, "privateStaticMethodCalculateThrowException", isA(int.class), isA(int.class)).thenReturn(expected);
        doReturn(expected).when(PowerMockSample.class, "privateStaticMethodCalculateThrowException", isA(int.class), isA(int.class));
        int actual = Whitebox.invokeMethod(PowerMockSample.class, "privateStaticMethodCalculateThrowException", 1, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void mockStatic_mockPrivateStaticMethodCalculateThrowException() throws Exception {
        int expected = 10;
        PowerMockito.mockStatic(PowerMockSample.class);
        //两者均可。均不会走真实逻辑。
//        when(PowerMockSample.class, "privateStaticMethodCalculateThrowException", isA(int.class), isA(int.class)).thenReturn(expected);
        doReturn(expected).when(PowerMockSample.class, "privateStaticMethodCalculateThrowException", isA(int.class), isA(int.class));
        int actual = Whitebox.invokeMethod(PowerMockSample.class, "privateStaticMethodCalculateThrowException", 1, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void mockStatic_mockPublicStaticMethodReturnStringButThrowException() {
        String newValue = "mockPublicStaticMethodReturnStringButThrowException";

        PowerMockito.mockStatic(PowerMockSample.class);

        //没有抛异常，所以没有走真实逻辑。返回了null。
        assertEquals(null, PowerMockSample.publicStaticMethodReturnStringButThrowException());

        when(PowerMockSample.publicStaticMethodReturnStringButThrowException()).thenReturn(newValue);
        //public static方法，不能用doReturn。会抛UnfinishedStubbingException异常。
//        doReturn(newValue).when(PowerMockSample.publicStaticMethodReturnStringButThrowException());
        assertEquals(newValue, PowerMockSample.publicStaticMethodReturnStringButThrowException());
    }

    @Test
    public void spyClass_mockPublicStaticMethodCalculate() throws Exception {
        int expected = 10;

        PowerMockito.spy(PowerMockSample.class);

        //会走真实逻辑。
        assertEquals(3, PowerMockSample.publicStaticMethodCalculate(1, 2));

//        when(PowerMockSample.publicStaticMethodCalculate(isA(int.class), isA(int.class))).thenReturn(expected);
        //不妥，会先走一遍真实逻辑
//        when(PowerMockSample.class,"publicStaticMethodCalculate",isA(int.class),isA(int.class)).thenReturn(expected);
        //会抛UnfinishedStubbingException异常。
//        doReturn(expected).when(PowerMockSample.publicStaticMethodCalculate(isA(int.class),isA(int.class)));
        doReturn(expected).when(PowerMockSample.class, "publicStaticMethodCalculate", isA(int.class), isA(int.class));
        assertEquals(expected, PowerMockSample.publicStaticMethodCalculate(1, 2));
    }

    @Test
    public void spyClass_mockPrivateStaticMethodNoReturnThrowException() throws Exception {
        PowerMockito.spy(PowerMockSample.class);

        try {
            Whitebox.invokeMethod(PowerMockSample.class, "privateStaticMethodNoReturnThrowException");
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }

        doNothing().when(PowerMockSample.class, "privateStaticMethodNoReturnThrowException");
        Whitebox.invokeMethod(PowerMockSample.class, "privateStaticMethodNoReturnThrowException");
    }

    @Test
    public void spyClass_mockPublicStaticMethodNoReturnThrowException() throws Exception {
        PowerMockito.spy(PowerMockSample.class);

        try {
            PowerMockSample.publicStaticMethodNoReturnThrowException();
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }

        //传的是mock过的class
        //没有返回值的方法，只能通过方法名
        doNothing().when(PowerMockSample.class, "publicStaticMethodNoReturnThrowException");

        PowerMockSample.publicStaticMethodNoReturnThrowException();
    }

    private Object getStaticFieldValue(Class<?> clazz, String fieldName) {
        return Whitebox.getFieldValue(Whitebox.getField(clazz, fieldName), clazz);
    }

    private Object getFieldValue(Class<?> clazz, String fieldName, Object object) {
        return Whitebox.getFieldValue(Whitebox.getField(clazz, fieldName), object);
    }
}