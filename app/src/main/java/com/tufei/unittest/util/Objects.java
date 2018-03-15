package com.tufei.unittest.util;

import android.support.annotation.Nullable;

import java.util.Arrays;

/**
 * guava库的工具类
 *
 * @author tufei
 * @date 2018/3/15.
 */
public class Objects {
    private Objects() {

    }

    public static boolean equal(@Nullable Object a, @Nullable Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static int hashCode(@Nullable Object... objects) {
        return Arrays.hashCode(objects);
    }
}
