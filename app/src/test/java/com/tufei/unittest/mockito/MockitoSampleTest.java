package com.tufei.unittest.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

/**
 * @author TuFei
 * @date 2018/4/2.
 */
public class MockitoSampleTest {

    @Mock
    MockitoSample mockitoSample;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mockPublicMethodNoReturnThrowException() {
        doNothing().when(mockitoSample).publicMethodNoReturnThrowException();
        mockitoSample.publicMethodNoReturnThrowException();
    }

    @Test
    public void mockPublicMethodReturnString() {
        String expected = "mockPublicMethodReturnString";
        doReturn(expected).when(mockitoSample).publicMethodReturnString();
        String actual = mockitoSample.publicMethodReturnString();
        assertEquals(expected,actual);
    }
}