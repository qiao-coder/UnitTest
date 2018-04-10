package com.tufei.unittest.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        //等同于：
        //mockSample = mock(MockitoSample.class);
        //spySample = spy(MockitoSample.class);
        MockitoAnnotations.initMocks(this);
    }

    /**
     * mock无返回值的方法:doNothing...when...
     */
    @Test
    public void mockPublicMethodNoReturnThrowException_doNothing_when() {
        //无返回值没有对应的when...thenReturn...
        doNothing().when(mockSample).publicMethodNoReturnThrowException();
        mockSample.publicMethodNoReturnThrowException();
    }

    /**
     * mock有返回值的方法:when...thenReturn...
     */
    @Test
    public void mockPublicMethodReturnString_when_thenReturn() {
        String expected = "mockPublicMethodReturnString";
        when(mockSample.publicMethodReturnString()).thenReturn(expected);
        String actual = mockSample.publicMethodReturnString();
        assertEquals(expected, actual);
    }

    /**
     * mock带参数的方法
     */
    @Test
    public void mockPublicMethodCalculate_withArgs(){
        int expected = 999;
        when(mockSample.publicMethodCalculate(isA(int.class),isA(int.class))).thenReturn(expected);
        int actual = mockSample.publicMethodCalculate(1, 2);
        assertEquals(expected,actual);
    }

    /**
     * mock有返回值的方法:doReturn...thenReturn...
     */
    @Test
    public void mockPublicMethodReturnString() {
        String expected = "mockPublicMethodReturnString";
        doReturn(expected).when(mockSample).publicMethodReturnString();
        String actual = mockSample.publicMethodReturnString();
        assertEquals(expected, actual);
    }

    @Test
    public void verify_publicMethodReturnString(){
        verify(mockSample,never()).publicMethodReturnString();

        mockSample.publicMethodReturnString();

        //默认情况下是times(1)。times(1)可以被省略。
        verify(mockSample).publicMethodReturnString();
        mockSample.publicMethodReturnString();
        verify(mockSample,times(2)).publicMethodReturnString();
    }

    @Test
    public void verify_publicMethodCalculate(){
        //可以不使用参数匹配器。
        verify(mockSample,never()).publicMethodCalculate(1,2);

        //如果你使用参数匹配器(isA(Class<T>)、anyXxx()、eq())，所有的参数都必须由匹配器提供。。
        verify(mockSample,never()).publicMethodCalculate(isA(int.class),isA(int.class));
        verify(mockSample,never()).publicMethodCalculate(anyInt(),anyInt());
        verify(mockSample,never()).publicMethodCalculate(eq(1),eq(2));

        mockSample.publicMethodCalculate(1,2);
        verify(mockSample).publicMethodCalculate(1,2);
        verify(mockSample).publicMethodCalculate(isA(int.class),isA(int.class));
        verify(mockSample).publicMethodCalculate(anyInt(),anyInt());
        verify(mockSample).publicMethodCalculate(eq(1),eq(2));
        verify(mockSample,never()).publicMethodCalculate(1,1);
        verify(mockSample,never()).publicMethodCalculate(eq(1),eq(1));

        mockSample.publicMethodCalculate(1,2);
        verify(mockSample,times(2)).publicMethodCalculate(1,2);
        verify(mockSample,times(2)).publicMethodCalculate(isA(int.class),isA(int.class));
        verify(mockSample,times(2)).publicMethodCalculate(anyInt(),anyInt());
        verify(mockSample,times(2)).publicMethodCalculate(eq(1),eq(2));
        verify(mockSample,never()).publicMethodCalculate(1,1);
        verify(mockSample,never()).publicMethodCalculate(eq(1),eq(1));
    }

    @Test
    public void notMockPublicMethodNoReturnThrowException_mockClass(){
        mockSample.publicMethodNoReturnThrowException();
    }

    @Test(expected = NullPointerException.class)
    public void notMockPublicMethodNoReturnThrowException_spyClass(){
        spySample.publicMethodNoReturnThrowException();
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
    public void notMockPublicMethodReturnString_spyClass() {
        String expected = "publicMethodReturnString";
        String actual = spySample.publicMethodReturnString();
        assertEquals(expected, actual);
    }

    /**
     * 基本类型：数值类型默认返回0。boolean类型默认返回false。
     */
    @Test
    public void notMockPublicMethodReturnInt_mockClass() {
        int actual = mockSample.publicMethodReturnInt();
        assertEquals(0, actual);
    }

    /**
     * 返回方法执行后的实际返回值。
     */
    @Test
    public void notMockPublicMethodReturnInt_spyClass() {
        int actual = spySample.publicMethodReturnInt();
        assertEquals(999, actual);
    }

    /**
     * Object：默认返回null。
     */
    @Test
    public void notMockPublicMethodReturnObject_mockClass() {
        MockitoSample.DataBean actual = mockSample.publicMethodReturnObject();
        assertEquals(null, actual);
    }

    /**
     * 返回方法执行后的实际返回值。
     */
    @Test
    public void notMockPublicMethodReturnObject_spyClass() {
        MockitoSample.DataBean actual = spySample.publicMethodReturnObject();
        assertEquals(new MockitoSample.DataBean(), actual);
    }

    /**
     * 集合：默认返回空集合，并非null
     */
    @Test
    public void notMockPublicMethodReturnList_mockClass() {
        List<Integer> actual = mockSample.publicMethodReturnList();
        assertEquals(new ArrayList<Integer>(), actual);
    }

    /**
     * 返回方法执行后的实际返回值。
     */
    @Test
    public void notMockPublicMethodReturnList_spyClass() {
        List<Integer> actual = spySample.publicMethodReturnList();
        assertEquals(Collections.singletonList(1), actual);
    }

    /**
     * 数组：默认返回null
     */
    @Test
    public void notMockPublicMethodReturnArray_mockClass() {
        int[] actual = mockSample.publicMethodReturnArray();
        assertArrayEquals(null, actual);
    }

    /**
     * 返回方法执行后的实际返回值。
     */
    @Test
    public void notMockPublicMethodReturnArray_spyClass() {
        int[] actual = spySample.publicMethodReturnArray();
        assertArrayEquals(new int[]{1}, actual);
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

    /**
     * 使用spy实例时，调用when...thenReturn...会走真实逻辑。spy(Object),其实就是通过
     * Object的getClass方法，来获取到其Class实例。跟spy(Class<T>)一样没有什么实际区别。
     * 看源码即可知道。
     */
    @Test(expected = NullPointerException.class)
    public void spyObject_spyPublicMethodReturnStringButThrowException_when_thenReturn() {
        MockitoSample mockitoSample = new MockitoSample();
        MockitoSample spy = spy(mockitoSample);
        when(spy.publicMethodReturnStringButThrowException()).thenReturn("test");
        spy.publicMethodReturnStringButThrowException();
    }
}