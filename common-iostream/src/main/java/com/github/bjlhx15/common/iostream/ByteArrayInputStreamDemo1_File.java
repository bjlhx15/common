package com.github.bjlhx15.common.iostream;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayInputStreamDemo1_File {
    //将字节数组写入文件（以字节数组的形式);
    public static void bytetoFile(String path, byte[] datas) {
        System.out.println(datas.length);
        File myFile = new File(path);
        ByteArrayInputStream bis = new ByteArrayInputStream(datas);
        try {
            int len;
            byte[] car = new byte[1024 * 10];
            FileOutputStream fileOutputStream = new FileOutputStream(myFile);
            //这里循环读取，不会内存泄漏
            while ((len = bis.read(car)) != -1) {
                fileOutputStream.write(car, 0, len);
                fileOutputStream.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {

        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : FileOutputStreamDemo.class.getClass().getResource("/").getPath();
        byte[] filetoByte = ByteArrayOutputStreamDemo1_File.filetoByte(path + "FileInputStreamDemo.txt");
        bytetoFile(path + "ByteArrayInputStreamDemo--001.txt",filetoByte);


    }
}
