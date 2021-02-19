package com.github.bjlhx15.common.springdemo.event.util;

/**
 * @author lihongxu6
 * @version 1.0
 * @className HuiWenString
 * @description TODO
 * @date 2021-01-14 10:34
 */
public class HuiWenString {

    public static String handleLongestHuiWen(String checkStr) {
        if (null == checkStr) {
            throw new RuntimeException("输入值不能为空！");
        }
        String oldStr = checkStr.replace(" ", "").replaceAll("\\pP", "");
//        System.out.println(oldStr);
        String newStr = oldStr.toLowerCase();

        if (oldStr.length() <= 1) {
            return oldStr;
        }

        int start = 0, end = 0;
        for (int i = 0; i < newStr.length(); i++) {
            int len1 = centerExpend(newStr, i, i);
            int len2 = centerExpend(newStr, i, i+1);
            int len=Math.max(len1,len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return oldStr.substring(start, end + 1);
    }

    private static int centerExpend(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            --left;
            ++right;
        }
        return right - left - 1;
    }
}
