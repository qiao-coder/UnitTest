package com.tufei.unittest;

/**
 * @author TuFei
 * @date 18-8-20.
 */
public class Test {
    int mInt = 1;
    String mString = "string";
    static int mStaticInt = 1;
    final int mFinalInt = 1;
    final String mFinalString = "finalString";
    final static int mFinalStaticInt = 1;

    public void test() {
        int _int = mInt;
        String string = mString;
        int staticInt = mStaticInt;
        int finalInt = mFinalInt;
        String finalString = mFinalString;
        int finalStaticInt = mFinalStaticInt;
    }
}
