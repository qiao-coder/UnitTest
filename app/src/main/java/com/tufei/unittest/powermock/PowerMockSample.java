package com.tufei.unittest.powermock;

/**
 * @author TuFei
 * @date 2018/3/21.
 */
public class PowerMockSample {

    private int privateInt = 1;
    private String privateString = "privateString";
    //本质上它是一个Object
    private Integer privateInteger = 1;

    private static int privateStaticInt = 1;
    private static String privateStaticString = "privateStaticString";
    private static Integer privateStaticInteger = 1;


    private final int privateFinalInt = 1;
    private final String privateFinalString = "privateFinalString";
    private final Integer privateFinalInteger = 1;


    private static final int privateStaticFinalInt = 1;
    private static final String privateStaticFinalString = "privateStaticFinalString";
    private static final Integer privateStaticFinalInteger = 1;

    public int publicInt = 1;
    public String publicString = "publicString";
    public Integer publicInteger = 1;

    public static int publicStaticInt = 1;
    public static String publicStaticString = "publicStaticString";
    public static Integer publicStaticInteger = 1;

    public final int publicFinalInt = 1;
    public final String publicFinalString = "publicFinalString";
    public final Integer publicFinalInteger = 1;

    public static final int publicStaticFinalInt = 1;
    public static final String publicStaticFinalString = "publicStaticFinalString";
    public static final Integer publicStaticFinalInteger = 1;

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
        if (true) {
            throw new NullPointerException();
        }
        return a + b;
    }

    public static String publicStaticMethodReturnStringButThrowException() {
        if (true) {
            throw new NullPointerException();
        }
        return "publicStaticMethodReturnStringButThrowException";
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

    //********************************下面这些方法，只是为了在单元测试里面验证mock的结果*******************************

    public int getPrivateInt() {
        return privateInt;
    }

    public Integer getPrivateInteger() {
        return privateInteger;
    }

    public static int getPrivateStaticInt() {
        return privateStaticInt;
    }

    public static Integer getPrivateStaticInteger() {
        return privateStaticInteger;
    }

    public int getPrivateFinalInt() {
        return privateFinalInt;
    }

    public String getPrivateFinalString() {
        return privateFinalString;
    }

    public Integer getPrivateFinalInteger() {
        return privateFinalInteger;
    }

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
