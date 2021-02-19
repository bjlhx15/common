package com.github.bjlhx15.common.threaddemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lihongxu6
 * @version 1.0
 * @className ThreadDemo01
 * @description TODO
 * @date 2021-02-13 11:00
 */
public class ThreadDemo03StringOom {
    public static void main(String[] args) {
        try {
            List<String> list = new ArrayList<String>();
            for (int i = 0; ; i++) {
                System.out.println(i);
                list.add(String.valueOf("String" + i++).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
