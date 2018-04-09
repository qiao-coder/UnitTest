package com.tufei.unittest.mockito;

/**
 * @author TuFei
 * @date 2018/4/8.
 */
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
