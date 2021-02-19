package com.github.bjlhx15.common.springdemo.event.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author lihongxu6
 * @version 1.0
 * @className HuiWenStringTest
 * @description TODO
 * @date 2021-01-14 11:15
 */
public class HuiWenStringTest {

    @Test
    public void handleLongestHuiWen() {
        String checkStr = "aba";
        String huiWen = HuiWenString.handleLongestHuiWen(checkStr);
        System.out.println(huiWen);
        Assert.assertEquals("aba",huiWen);

        checkStr = "aba dfgfd";
        huiWen = HuiWenString.handleLongestHuiWen(checkStr);
        System.out.println(huiWen);
        Assert.assertEquals("dfgfd",huiWen);

        checkStr = "aaaaa";
        huiWen = HuiWenString.handleLongestHuiWen(checkStr);
        System.out.println(huiWen);
        Assert.assertEquals("aaaaa",huiWen);


        checkStr = "a";
        huiWen = HuiWenString.handleLongestHuiWen(checkStr);
        System.out.println(huiWen);
        Assert.assertEquals("a",huiWen);


        checkStr = "aa";
        huiWen = HuiWenString.handleLongestHuiWen(checkStr);
        System.out.println(huiWen);
        Assert.assertEquals("aa",huiWen);

        checkStr = "";
        huiWen = HuiWenString.handleLongestHuiWen(checkStr);
        System.out.println(huiWen);
        Assert.assertEquals("",huiWen);


        checkStr = "Kobe Bryant say: Madam ImAdam.  XXYXX";
        huiWen = HuiWenString.handleLongestHuiWen(checkStr);
        System.out.println(huiWen);
        Assert.assertEquals("MadamImAdam",huiWen);


        checkStr = "Kobe Bryant!@#$%^&*() say: Madam !,.'ImAdam.  XYYYXXXXYXX<?<>?>><XXYYYX";
        huiWen = HuiWenString.handleLongestHuiWen(checkStr);
        System.out.println(huiWen);
        Assert.assertEquals("MadamImAdam",huiWen);
    }

    @Test(expected = RuntimeException.class)
    public void handleLongestHuiWenNull() {
        String checkStr = null;
        String huiWen = HuiWenString.handleLongestHuiWen(checkStr);
    }
}