package com.tufei.unittest.powermock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
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
    public void mockPrivateFiled() {
        String newValue = "mockPrivateFiled";

        //第一个参数传的是实例
        Whitebox.setInternalState(powerMockSample, "privateFiled", newValue);

        assertEquals(newValue, powerMockSample.getPrivateFiled());
    }

    @Test
    public void mockPrivateStaticField() {
        String newValue = "mockPrivateStaticField";

        //第一个参数，传的是class
        Whitebox.setInternalState(PowerMockSample.class, "privateStaticField", newValue);

        assertEquals(newValue, PowerMockSample.getPrivateStaticField());
    }

    @Test
    public void mockPrivateStaticFinalField() {
        String newValue = "mockPrivateStaticFinalField";

        //第一个参数，传的是class
        Whitebox.setInternalState(PowerMockSample.class, "privateStaticFinalField", newValue);

        //mock失败
        assertNotEquals(newValue, PowerMockSample.getPrivateStaticFinalField());
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
    public void mockPublicStaticFinalField() {
        String newValue = "mockPublicStaticFinalField";

        //第一个参数，传的是class
        Whitebox.setInternalState(PowerMockSample.class, "publicStaticFinalField", newValue);

        //mock失败
        assertNotEquals(newValue, PowerMockSample.publicStaticFinalField);
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
    public void mockPrivateMethodCalculate() throws Exception {
        int expected = 10;

        powerMockSample = PowerMockito.spy(powerMockSample);

        assertEquals(3, powerMockSample.callPrivateMethodCalculate(1, 2));

        when(powerMockSample, "privateMethodCalculate", isA(int.class), isA(int.class)).thenReturn(expected);

        assertEquals(expected, powerMockSample.callPrivateMethodCalculate(1, 2));
    }

    @Test
    public void mockPrivateStaticMethodReturnString() throws Exception {
        String oldValue = "privateStaticMethodReturnString";
        String newValue = "mockPrivateStaticMethodReturnString";

        PowerMockito.spy(PowerMockSample.class);

        assertEquals(oldValue, PowerMockSample.callPrivateStaticMethodReturnString());

        //传的是mock过的class
        when(PowerMockSample.class, "privateStaticMethodReturnString").thenReturn(newValue);

        assertEquals(newValue, PowerMockSample.callPrivateStaticMethodReturnString());
    }

    @Test
    public void mockPrivateStaticMethodCalculate() throws Exception {
        int expected = 10;

        PowerMockito.spy(PowerMockSample.class);

        assertEquals(3, PowerMockSample.callPrivateStaticMethodCalculate(1, 2));

        //传的是mock过的class
        when(PowerMockSample.class, "privateStaticMethodCalculate", isA(int.class), isA(int.class)).thenReturn(expected);

        assertEquals(expected, PowerMockSample.callPrivateStaticMethodCalculate(1, 2));
    }

    @Test
    public void mockPublicStaticMethodReturnString() {
        String oldValue = "publicStaticMethodReturnString";
        String newValue = "mockPublicStaticMethodReturnString";

        PowerMockito.spy(PowerMockSample.class);

        assertEquals(oldValue, PowerMockSample.publicStaticMethodReturnString());

        //传的是mock过的class
        when(PowerMockSample.publicStaticMethodReturnString()).thenReturn(newValue);

        assertEquals(newValue, PowerMockSample.publicStaticMethodReturnString());
    }

    @Test
    public void mockPublicStaticMethodCalculate() {
        int expected = 10;

        PowerMockito.spy(PowerMockSample.class);

        assertEquals(3, PowerMockSample.publicStaticMethodCalculate(1, 2));

        //传的是mock过的class
        when(PowerMockSample.publicStaticMethodCalculate(isA(int.class), isA(int.class))).thenReturn(expected);

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