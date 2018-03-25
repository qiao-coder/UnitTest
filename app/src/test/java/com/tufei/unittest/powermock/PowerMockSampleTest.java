package com.tufei.unittest.powermock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.spy;
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

        //不行，会报空指针
        //PowerMockito.spy(PowerMockSample.class);
        powerMockSample = PowerMockito.spy(powerMockSample);

        assertEquals(oleValue, powerMockSample.callPrivateMethodReturnString());

        //也可以
        //when(powerMockSample, "privateMethodReturnString").thenReturn(newValue);
        doReturn(newValue).when(powerMockSample, "privateMethodReturnString");

        assertEquals(newValue, powerMockSample.callPrivateMethodReturnString());
    }
}