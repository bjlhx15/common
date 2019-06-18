package com.github.bjlhx15.common.iostream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteArrayInputStreamDemo2_String {
    public static void main(String[] args) throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream("我是内存流测试".getBytes());
        byte[] b = new byte[1024];
        int len;
        while((len = bais.read(b)) != -1){
            System.out.println(new String(b, 0, len));
        }

    }
}
