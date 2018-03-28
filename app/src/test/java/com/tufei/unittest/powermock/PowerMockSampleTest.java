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
    public void mockPrivateString() {
        powerMockSample = new PowerMockSample();

        String newValue = "mockPrivateString";

        //mock非静态变量，第一个参数传的是实例
        Whitebox.setInternalState(powerMockSample, "privateString", newValue);

        assertEquals(newValue, powerMockSample.getPrivateString());
    }

    @Test
    public void mockPrivateStaticString() {
        String newValue = "mockPrivateStaticString";

        //mock静态变量，第一个参数传的是class
        Whitebox.setInternalState(PowerMockSample.class, "privateStaticString", newValue);

        assertEquals(newValue, PowerMockSample.getPrivateStaticString());
    }

    @Test
    public void mockPrivateStaticFinalString() {
        String newValue = "mockPrivateStaticFinalString";

        //第一个参数，传的是class
        Whitebox.setInternalState(PowerMockSample.class, "privateStaticFinalString", newValue);

        //mock失败
        assertNotEquals(newValue, PowerMockSample.getPrivateStaticFinalString());
    }

    @Test
    public void mockPrivateStaticFinalInt() {
        int newValue = 2;

        //第一个参数，传的是class
        Whitebox.setInternalState(PowerMockSample.class, "privateStaticFinalInt", newValue);

        //mock失败
        assertNotEquals(newValue, PowerMockSample.getPrivateStaticFinalInt());
    }

    @Test
    public void mockPrivateStaticFinalInteger() {
        int newValue = 2;

        //第一个参数，传的是class
        Whitebox.setInternalState(PowerMockSample.class, "privateStaticFinalInteger", newValue);

        assertEquals(newValue, PowerMockSample.getPrivateStaticFinalInteger());
    }

    @Test
    public void mockPublicStaticFinalString() {
        String newValue = "mockPublicStaticFinalString";

        //第一个参数，传的是class
        Whitebox.setInternalState(PowerMockSample.class, "publicStaticFinalString", newValue);

        //mock失败
        assertNotEquals(newValue, PowerMockSample.publicStaticFinalString);
    }

    @Test
    public void mockPrivateMethodCalculateThrowException() throws Exception {
        int expected = 10;

        powerMockSample = PowerMockito.spy(powerMockSample);

        //不要使用when(...).thenReturn(...)。会调用你想要mock的方法的真实逻辑。然后才返回mock的结果。
        //doReturn方法的注释，也提供了相关解释和例子。
//        when(powerMockSample, "privateMethodCalculateThrowException", isA(int.class), isA(int.class)).thenReturn(expected);
        doReturn(expected).when(powerMockSample, "privateMethodCalculateThrowException", isA(int.class), isA(int.class));

        assertEquals(expected, powerMockSample.callPrivateMethodCalculateThrowException(1, 2));
    }

    @Test
    public void mockPrivateMethodCalculate() throws Exception {
        int expected = 10;

        powerMockSample = PowerMockito.spy(powerMockSample);

        assertEquals(3, powerMockSample.callPrivateMethodCalculate(1, 2));

        doReturn(expected).when(powerMockSample, "privateMethodCalculate", isA(int.class), isA(int.class));

        assertEquals(expected, powerMockSample.callPrivateMethodCalculate(1, 2));
    }

    @Test
    public void mockPrivateMethodReturnString() throws Exception {
        String oleValue = "privateMethodReturnString";
        String newValue = "mockPrivateMethodReturnString";

        //不行，该方法没有返回值。而且在doReturn时，会报空指针。
        //PowerMockito.spy(PowerMockSample.class);
        powerMockSample = PowerMockito.spy(powerMockSample);

        assertEquals(oleValue, powerMockSample.callPrivateMethodReturnString());

        //也可以
        //when(powerMockSample, "privateMethodReturnString").thenReturn(newValue);
        doReturn(newValue).when(powerMockSample, "privateMethodReturnString");

        assertEquals(newValue, powerMockSample.callPrivateMethodReturnString());
    }

    @Test
    public void mockPrivateStaticMethodReturnString() throws Exception {
        String oldValue = "privateStaticMethodReturnString";
        String newValue = "mockPrivateStaticMethodReturnString";

        PowerMockito.spy(PowerMockSample.class);

        assertEquals(oldValue, PowerMockSample.callPrivateStaticMethodReturnString());

        //传的是mock过的class
        doReturn(newValue).when(PowerMockSample.class, "privateStaticMethodReturnString");

        assertEquals(newValue, PowerMockSample.callPrivateStaticMethodReturnString());
    }

    @Test
    public void mockPrivateStaticMethodCalculate() throws Exception {
        int expected = 10;

        PowerMockito.spy(PowerMockSample.class);

//        assertEquals(3, PowerMockSample.callPrivateStaticMethodCalculate(1, 2));

        //传的是mock过的class
        doReturn(expected).when(PowerMockSample.class, "privateStaticMethodCalculate", isA(int.class), isA(int.class));
        //不可行，还是会走实践逻辑
//        when(PowerMockSample.class, "privateStaticMethodCalculate", isA(int.class), isA(int.class)).thenReturn(expected);
        assertEquals(expected, PowerMockSample.callPrivateStaticMethodCalculate(1, 2));
    }

    @Test
    public void mockPublicStaticMethodReturnString() {
        String oldValue = "publicStaticMethodReturnString";
        String newValue = "mockPublicStaticMethodReturnString";

        PowerMockito.mockStatic(PowerMockSample.class);

//        assertEquals(oldValue, PowerMockSample.publicStaticMethodReturnString());

        //传的是mock过的class
        when(PowerMockSample.publicStaticMethodReturnString()).thenReturn(newValue);
        //public static方法，不能用doReturn。会抛UnfinishedStubbingException异常。
//        doReturn(newValue).when(PowerMockSample.publicStaticMethodReturnString());

        assertEquals(newValue, PowerMockSample.publicStaticMethodReturnString());
    }

    @Test
    public void mockPublicStaticMethodCalculate() {
        int expected = 10;

        PowerMockito.spy(PowerMockSample.class);

        assertEquals(3, PowerMockSample.publicStaticMethodCalculate(1, 2));

        //传的是mock过的class
        when(PowerMockSample.publicStaticMethodCalculate(isA(int.class), isA(int.class))).thenReturn(expected);
        //会抛UnfinishedStubbingException异常。
//        doReturn(expected).when(PowerMockSample.publicStaticMethodCalculate(isA(int.class),isA(int.class)));

        assertEquals(expected, PowerMockSample.publicStaticMethodCalculate(1, 2));
    }

    @Test
    public void mockPrivateMethodNoReturnThrowException() throws Exception {
        powerMockSample = PowerMockito.spy(powerMockSample);

        try {
            powerMockSample.callPrivateMethodNoReturnThrowException();
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }

        //传的是mock过的实例
        doNothing().when(powerMockSample, "privateMethodNoReturnThrowException");

        powerMockSample.callPrivateMethodNoReturnThrowException();
    }

    @Test
    public void mockPrivateStaticMethodNoReturnThrowException() throws Exception {
        PowerMockito.spy(PowerMockSample.class);

        try {
            PowerMockSample.callPrivateStaticMethodNoReturnThrowException();
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }

        //传的是mock过的class
        doNothing().when(PowerMockSample.class, "privateStaticMethodNoReturnThrowException");

        PowerMockSample.callPrivateStaticMethodNoReturnThrowException();
    }

    @Test
    public void mockPublicStaticMethodNoReturnThrowException() throws Exception {
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
}