package com.github.bjlhx15.common.iostream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderDemo {
    public static void main(String[] args) throws IOException {
        try {
            // 将System.in对象转化为Reader对象
            InputStreamReader reader = new InputStreamReader(System.in);
            //将普通的Reader包装成BufferedReader
            BufferedReader bufferedReader = new BufferedReader(reader);
            String buffer = null;
            while ((buffer = bufferedReader.readLine()) != null) {
                // 如果读取到的字符串为“exit”,则程序退出
                if (buffer.equals("exit")) {
                    System.exit(1);
                }
                //打印读取的内容
                System.out.print("输入内容：" + buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
