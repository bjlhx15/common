package com.github.bjlhx15.common.springdemo.event.testregex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lihongxu6
 * @version 1.0
 * @className TestRegexNum
 * @description TODO
 * @date 2021-01-15 13:22
 */
public class TestStringRegex {


    @Test
    public void testString() {
        boolean temp = "1983-07-27".matches("\\d{4}-\\d{2}-\\d{2}") ;
        System.out.println("字符串验证：" + temp) ;

        String str1 = "A1B22C333D4444E55555F".replaceAll("\\d+","_") ;
        System.out.println("字符串替换操作：" + str1) ;

        String s[] = "A1B22C333D4444E55555F".split("\\d+") ;
        System.out.print("字符串的拆分：") ;
        for(int x=0;x<s.length;x++){
            System.out.print(s[x] + "\t") ;
        }
    }

    @Test
    public void testPS() {
        String string="测试<>《》！*(^)$%~!@#$…&%￥—+=、。，；‘’“”：·`文本";

        System.out.println(string.replaceAll("\\p{P}|\\pS", ""));

    }
}
