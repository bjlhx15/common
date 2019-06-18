package com.github.bjlhx15.common.iochar;

import com.github.bjlhx15.common.iostream.FileOutputStreamDemo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileWriterDemo {

    public static void main(String[] args) throws IOException {
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : FileOutputStreamDemo.class.getClass().getResource("/").getPath();
        //第一步：定义要输出的文件的File类对象
        File file = new File(path + "FileWriterDemo.txt");//你的路径
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Writer out = new FileWriter(file);
        String str = "一定要好好学习，天天向上。。。";
        out.write(str);
        out.close();
    }
}

