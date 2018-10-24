package com.facedamon.smart;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class Unit {

    @Test
    public void test() {
        System.out.println(StandardCharsets.UTF_8.displayName());
    }

    @Test
    public void test2() {
        System.out.println(builder());
    }

    private String builder() {
        StringBuffer sb = new StringBuffer()
                .append("                            _ooOoo_                     \n")
                .append("                           o8888888o                    \n")
                .append("                           88  .  88                    \n")
                .append("                           (| -_- |)                    \n");
        return sb.toString();
    }
}
