package com.tufei.unittest.powermock;

/**
 * @author TuFei
 * @date 2018/3/21.
 */
public class PowerMockSample {

    private String privateFiled = "privateFiled";

    private static String privateStaticField = "privateStaticField";

    private static final String privateStaticFinalField = "privateStaticFinalField";

    private static final int privateStaticFinalInt = 1;

    private static final Integer privateStaticFinalInteger = 1;

    public static final String publicStaticFinalField = "publicStaticFinalField";

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
        return a + b;
    }

    public static String publicStaticMethodReturnString() {
        return "publicStaticMethodReturnString";
    }

    public static int publicStaticMethodCalculate(int a, int b) {
        return a + b;
    }

    private void privateMethodNoRetrunThrowException() {
        throw new NullPointerException();
    }

    private static void privateStaticMethodNoRetrunThrowException() {
        throw new NullPointerException();
    }

    public static void publicStaticMethodNoRetrunThrowException() {
        throw new NullPointerException();
    }

    //********************************下面这些方法，只是为了验证测试结果*******************************

    public String getPrivateFiled() {
        return privateFiled;
    }

    public static String getPrivateStaticField() {
        return privateStaticField;
    }

    public static String getPrivateStaticFinalField() {
        return privateStaticFinalField;
    }

    public static int getPrivateStaticFinalInt() {
        return privateStaticFinalInt;
    }

    public static int getPrivateStaticFinalInteger() {
        return privateStaticFinalInteger;
    }

    public String callPrivateMethodReturnString() {
        return privateMethodReturnString();
    }
}
