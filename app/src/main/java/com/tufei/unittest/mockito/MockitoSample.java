package com.tufei.unittest.mockito;

/**
 * @author TuFei
 * @date 2018/4/2.
 */
public class MockitoSample {
    public String publicString = "publicString";
    public int publicInt = 1;
    public Integer publicInteger = 1;

    public void publicMethodNoReturnThrowException() {
        throw new NullPointerException();
    }

    public String publicMethodReturnString() {
        return "publicMethodReturnString";
    }

    public String publicMethodReturnStringButThrowException() {
        throw new NullPointerException();
    }

    public int publicMethodCalculate(int a, int b) {
        return a + b;
    }

}
