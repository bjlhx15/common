package com.github.bjlhx15.common.iostream;

import com.github.bjlhx15.common.iostream.FileOutputStreamDemo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ByteArrayOutputStreamDemo1_File {

    //将图片写入程序（以字节数组的形式);
    public static byte[] filetoByte(String path) {
        File myFile = new File(path);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            FileInputStream myInputStream = new FileInputStream(myFile);
            int len = -1;
            byte[] car = new byte[128];//这里循环读取，不会内存泄漏
            while ((len = myInputStream.read(car)) != -1) {
                bos.write(car, 0, len);
                bos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return bos.toByteArray();
    }

    public static void main(String[] args) {

        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : FileOutputStreamDemo.class.getClass().getResource("/").getPath();

        byte[] bytes = filetoByte(path + "FileInputStreamDemo.txt");


        System.out.println("读取的内容【" + new String(bytes, 0, bytes.length) + "】");
    }
}
