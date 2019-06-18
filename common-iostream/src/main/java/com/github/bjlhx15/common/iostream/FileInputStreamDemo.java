package com.github.bjlhx15.common.iostream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileInputStreamDemo {
    public static void main(String[] args) throws IOException {
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : FileOutputStreamDemo.class.getClass().getResource("/").getPath();
        //第一步：定义要输出的文件的File类对象
        File file = new File(path + "FileInputStreamDemo.txt");
        //第二步：实例化InputStream
        InputStream input = new FileInputStream(file);
        //实现数据的读取操作
        byte data[] = new byte[128];
        int len = input.read(data);//将数据读取到数组之中
        System.out.println("读取的内容【" + new String(data, 0, len) + "】");
        //第四步关闭输入流
        input.close();
    }
}
