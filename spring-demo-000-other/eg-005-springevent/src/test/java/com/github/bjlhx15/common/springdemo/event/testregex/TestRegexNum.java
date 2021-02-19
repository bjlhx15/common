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
public class TestRegexNum {


    @Test
    public void testDate() {
        String str = "1993-07-27";        // 指定好一个日期格式的字符串
        String pat = "\\d{4}-\\d{2}-\\d{2}";    // 指定好正则表达式
        Pattern p = Pattern.compile(pat);    // 实例化Pattern类
        Matcher m = p.matcher(str);    // 实例化Matcher类
        if (m.matches()) {        // 进行验证的匹配，使用正则
            System.out.println("日期格式合法！");
        } else {
            System.out.println("日期格式不合法！");
        }
        //日期格式合法！
    }

    @Test
    public void testSplit() {
        // 要求将里面的字符取出，也就是说按照数字拆分
        String str = "A1B22C333D4444E55555F" ;	// 指定好一个字符串
        String pat = "\\d+" ;	// 指定好正则表达式
        Pattern p = Pattern.compile(pat) ;	// 实例化Pattern类
        String s[] = p.split(str) ;	// 执行拆分操作
        for(int x=0;x<s.length;x++){
            System.out.print(s[x] + "\t") ;
        }
        //A B	C	D	E	F
    }

    @Test
    public void testReplace() {
        // 要求将里面的字符取出，也就是说按照数字拆分
        String str = "A1B22C333D4444E55555F" ;	// 指定好一个字符串
        String pat = "\\d+" ;	// 指定好正则表达式
        Pattern p = Pattern.compile(pat) ;	// 实例化Pattern类
        Matcher m = p.matcher(str) ;	// 实例化Matcher类的对象
        String newString = m.replaceAll("_") ;
        System.out.println(newString) ;
        //A_B_C_D_E_F
    }
}
