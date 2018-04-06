package com.tufei.unittest.mockito;

import java.util.Collections;
import java.util.List;

/**
 * @author TuFei
 * @date 2018/4/2.
 */
public class MockitoSample {
    public String publicString = "publicString";
    public int publicInt = 1;
    public Integer publicInteger = 1;

    static class DataBean{
        int data = 0;
        @Override
        public boolean equals(Object obj) {
            return obj != null && obj instanceof DataBean && ((DataBean) obj).data == this.data;
        }
    }

    public void publicMethodNoReturnThrowException() {
        throw new NullPointerException();
    }

    public String publicMethodReturnString() {
        return "publicMethodReturnString";
    }

    public int publicMethodReturnInt(){
        return 999;
    }

    public DataBean publicMethodReturnObject(){
        return new DataBean();
    }

    public List<Integer> publicMethodReturnList(){
        return Collections.singletonList(1);
    }

    public int[] publicMethodReturnArray(){
        return new int[]{1};
    }

    public String publicMethodReturnStringButThrowException() {
        throw new NullPointerException();
    }

    public int publicMethodCalculate(int a, int b) {
        return a + b;
    }

}
