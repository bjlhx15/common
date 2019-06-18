package com.github.bjlhx15.common.iostream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileInputStreamSingleDemo {
    public static void main(String[] args) throws IOException {
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : FileOutputStreamDemo.class.getClass().getResource("/").getPath();
        //第一步：定义要输出的文件的File类对象
        File file = new File(path + "FileInputStreamDemo.txt");
        //第二步：实例化InputStream
        InputStream input = new FileInputStream(file);
        //实现数据的读取操作
        byte data[] = new byte[1024];
        int foot = 0;//控制保存的脚标索引
        int temp = 0;//接收每次保存的字节数据
        //while循环实际上按照由里向外的原则执行，它的执行分为两步：
        //第一步：“temp = input.read()”，表示读取一个字节保存到temp变量之中。
        //第二步：“(temp = input.read()) != -1”判断读取出来的temp内容是否为-1，
        //如果不是-1则表示还有数据，则进行保存。
        while((temp = input.read()) != -1){
            data[foot ++] = (byte) temp;
        }
        System.out.println("读取的内容【" +new String(data,0,foot)+"】");
        //第四步关闭输入流
        input.close();
    }
}
