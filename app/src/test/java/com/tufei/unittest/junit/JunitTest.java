package com.tufei.unittest.junit;

import com.tufei.unittest.Util;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author tufei
 * @date 2018/2/28.
 */

public class JunitTest {
    /**
     * 测试一个有返回值的方法
     */
    @Test
    public void testMethodWithReturn() {
        int actual = Util.add(1, 2);
        assertEquals(3, actual);
    }

    /**
     * 测试一个没有返回值，有副作用的方法
     */
    @Test
    public void testMethodWithSideEffect() {
        List<String> strings = Arrays.asList("a", "d", "b", "c");
        List<String> excepted = Arrays.asList("a", "b", "c", "d");
        //测试集合工具类的排序方法
        Collections.sort(strings);
        assertEquals(excepted, strings);
    }

    /**
     * 测试一个方法抛异常的方案1
     */
    @Test
    public void testMethodThrowException() {
        Exception actual = null;
        try {
            Util.throwException();
        } catch (Exception e) {
            actual = e;
        }
        assertEquals(IllegalArgumentException.class, actual.getClass());
    }

    /**
     * 测试一个方法抛异常时的方案2
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMethodThrowException2() {
        Util.throwException();
    }


}
