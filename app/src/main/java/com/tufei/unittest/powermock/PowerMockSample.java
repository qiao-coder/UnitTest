package com.tufei.unittest.powermock;

/**
 * @author TuFei
 * @date 2018/3/21.
 */
public class PowerMockSample {

    private String privateFiled = "privateFiled";

    private static String privateStaticField = "privateStaticField";

    private static final String privateStaticFinalField = "privateStaticFinalField";

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

    public String getPrivateFiled() {
        return privateFiled;
    }
}
