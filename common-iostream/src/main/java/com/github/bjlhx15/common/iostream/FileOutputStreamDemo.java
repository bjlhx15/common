package com.github.bjlhx15.common.iostream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : FileOutputStreamDemo.class.getClass().getResource("/").getPath();


        System.out.println(path);
        //第一步：定义要输出的文件的File类对象
        File file = new File(path + "FileOutputStreamDemo.txt");
        //输出信息的时候文件可以不存在，但是目录必须存在
        if (!file.getParentFile().exists()) {//父路径不存在
            file.getParentFile().mkdirs();//创建父路径
        }
        //第二步：利用OutputStream的子类为父类进行实例化
        OutputStream output = new FileOutputStream(file);
        // OutputStream output = new FileOutputStream(file,true);//此处为追加操作
        //第三步：输出文字信息
        String msg = "富则达济天下，穷则独善其身";//字符串
        //为了方便输出需要将字符串变为字节数组
        byte data[] = msg.getBytes();//变为字节数组
        // 全部输出数据
        output.write(data);
        // 输出部分数据
        // output.write(data,0,10);

        // 单个字节输出数据
        //        for (int x = 0; x < data.length; x++) {
        //            output.write(data[x]);
        //        }
        output.close();//关闭流
    }
}
