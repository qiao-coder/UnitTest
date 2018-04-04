package com.tufei.unittest.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * @author TuFei
 * @date 2018/4/2.
 */
public class MockitoSampleTest {

    @Mock
    private MockitoSample mockSample;
    @Spy
    private MockitoSample spySample;

    @Before
    public void setup() {
        //当有多个要mock或者spy的类。不需要一个个调用mock(Class(T))。这更简洁。
        MockitoAnnotations.initMocks(this);
    }

    /**
     * mock无返回值的方法
     */
    @Test
    public void mockPublicMethodNoReturnThrowException() {
        //无返回值没有对应的when...thenReturn...
        doNothing().when(mockSample).publicMethodNoReturnThrowException();
        mockSample.publicMethodNoReturnThrowException();
    }

    /**
     * mock有返回值的方法
     */
    @Test
    public void mockPublicMethodReturnString() {
        String expected = "mockPublicMethodReturnString";
        doReturn(expected).when(mockSample).publicMethodReturnString();
        String actual = mockSample.publicMethodReturnString();
        assertEquals(expected, actual);
    }

    /**
     * mock的类得到的实例，调用返回值为String的方法时，如果不mock该方法，
     * 则默认的返回值是null。
     */
    @Test
    public void notMockPublicMethodReturnString_mockClass() {
        String actual = mockSample.publicMethodReturnString();
        assertEquals(null, actual);
    }

    /**
     * spy的类得到的实例，调用返回值为String的方法时，如果不mock该方法，
     * 则默认的返回值是方法运行真实逻辑后返回的值。
     */
    @Test
    public void notSpyPublicMethodReturnString_spyClass() {
        String expected = "publicMethodReturnString";
        String actual = spySample.publicMethodReturnString();
        assertEquals(expected, actual);
    }

    /**
     * 使用mock时，调用when...thenReturn...不会走真实逻辑。
     */
    @Test
    public void mockPublicMethodReturnStringButThrowException_when_thenReturn() {
        String expected = "test";
        when(mockSample.publicMethodReturnStringButThrowException()).thenReturn(expected);
        String actual = mockSample.publicMethodReturnStringButThrowException();
        assertEquals(expected, actual);
    }

    /**
     * 使用spy时，调用when...thenReturn...会走真实逻辑。
     */
    @Test(expected = NullPointerException.class)
    public void spyPublicMethodReturnStringButThrowException_when_thenReturn() {
        when(spySample.publicMethodReturnStringButThrowException()).thenReturn("test");
        mockSample.publicMethodReturnStringButThrowException();
    }
}