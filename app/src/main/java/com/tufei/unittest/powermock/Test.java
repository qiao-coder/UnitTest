package com.tufei.unittest.powermock;

/**
 * @author TuFei
 * @date 2018/6/11.
 */
public class Test {
    private String privateString = "privateString";
    private final String privateFinalString = "privateFinalString";

    private static String privateStaticString = "privateStaticString";

    private final String privateFinalStringByNew = new String("privateFinalStringByNew") ;

    private static final String privateStaticFinalString = "privateStaticFinalString";

    private int privateInt = 1;

    private final int privateFinalInt = 1;
    private static int privateStaticInt = 1;

    private static final int privateStaticFinalInt = 1;

    private static final Integer privateStaticFinalInteger = 1;

    public static final String publicStaticFinalString = "publicStaticFinalString";

    public void method() {
        String privateString = this.privateString;
        String privateFinalString = this.privateFinalString;
        String privateStaticString = Test.privateStaticString;
        String privateFinalStringByNew = this.privateFinalStringByNew;
        String privateStaticFinalString = Test.privateStaticFinalString;
        int privateInt = this.privateInt;
        int privateFinalInt = this.privateFinalInt;
        int privateStaticInt = Test.privateStaticInt;
        int privateStaticFinalInt = Test.privateStaticFinalInt;
        Integer privateStaticFinalInteger = Test.privateStaticFinalInteger;
        String publicStaticFinalString = Test.publicStaticFinalString;
    }
}
