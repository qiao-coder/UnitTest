package com.tufei.unittest.powermock;

/**
 * @author TuFei
 * @date 2018/3/21.
 */
public class PowerMockSample {

    private String privateString = "privateString";

    private static String privateStaticString = "privateStaticString";

    private static final String privateStaticFinalString = "privateStaticFinalString";

    private static final int privateStaticFinalInt = 1;

    private static final Integer privateStaticFinalInteger = 1;

    public static final String publicStaticFinalString = "publicStaticFinalString";

    private int privateMethodCalculateThrowException(int a, int b) {
        System.out.println("a = " + a + ",b = " + b);
        throw new NullPointerException();
    }

    private String privateMethodReturnString() {
        return "privateMethodReturnString";
    }

    private int privateMethodCalculate(int a, int b) {
        return a + b;
    }

    private static String privateStaticMethodReturnString() {
        return "privateStaticMethodReturnString";
    }

    private static int privateStaticMethodCalculate(int a, int b) {
        if(true){
            throw new  NullPointerException();
        }
        return a + b;
    }

    public static String publicStaticMethodReturnString() {
        if(true){
            throw new NullPointerException();
        }
        return "publicStaticMethodReturnString";
    }

    public static int publicStaticMethodCalculate(int a, int b) {
        return a + b;
    }

    private void privateMethodNoReturnThrowException() {
        throw new NullPointerException();
    }

    private static void privateStaticMethodNoReturnThrowException() {
        throw new NullPointerException();
    }

    public static void publicStaticMethodNoReturnThrowException() {
        throw new NullPointerException();
    }

    //********************************下面这些方法，只是为了验证测试结果*******************************

    public String getPrivateString() {
        return privateString;
    }

    public static String getPrivateStaticString() {
        return privateStaticString;
    }

    public static String getPrivateStaticFinalString() {
        return privateStaticFinalString;
    }

    public static int getPrivateStaticFinalInt() {
        return privateStaticFinalInt;
    }

    public static int getPrivateStaticFinalInteger() {
        return privateStaticFinalInteger;
    }

    public int callPrivateMethodCalculateThrowException(int a, int b) {
        return privateMethodCalculateThrowException(a, b);
    }

    public int callPrivateMethodCalculate(int a, int b) {
        return privateMethodCalculate(a, b);
    }

    public String callPrivateMethodReturnString() {
        return privateMethodReturnString();
    }

    public static String callPrivateStaticMethodReturnString() {
        return privateStaticMethodReturnString();
    }

    public static int callPrivateStaticMethodCalculate(int a, int b) {
        return privateStaticMethodCalculate(a, b);
    }

    public void callPrivateMethodNoReturnThrowException() {
        privateMethodNoReturnThrowException();
    }

    public static void callPrivateStaticMethodNoReturnThrowException() {
        privateStaticMethodNoReturnThrowException();
    }
}
