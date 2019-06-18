package com.github.bjlhx15.common.iostream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayOutputStreamDemo2_String {

    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write("我是内存流测试".getBytes());
        //输出方式一
        byte[] b = baos.toByteArray();
        System.out.println(new String(b));
        //输出方式二
        String s = baos.toString();
        System.out.println(s);
    }
}
