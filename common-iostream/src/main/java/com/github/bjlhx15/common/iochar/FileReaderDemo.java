package com.github.bjlhx15.common.iochar;

import com.github.bjlhx15.common.iostream.FileOutputStreamDemo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileReaderDemo {
    public static void main(String[] args) throws IOException {
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : FileOutputStreamDemo.class.getClass().getResource("/").getPath();
        //第一步：定义要输出的文件的File类对象
        File file = new File(path + "FileWriterDemo.txt");//你的路径
        if (file.exists()) {
            Reader in = new FileReader(file);
            char data[] = new char[1024];
            int len = in.read(data);//向字符数组保存数据，返回长度。
            System.out.println(new String(data, 0, len));
            in.close();
        }
    }
}
