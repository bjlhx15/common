package com.github.bjlhx15.common.iostream;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BufferedStreamDemo {
    /**
     * 利用缓冲流进行拷贝，一个一个字节拷贝
     * @throws Exception
     */
    @Test
    public void t2() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : BufferedStreamDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"FileInputStreamDemo.txt";
        String copyfilepath=path+"FileInputStreamDemo--2.txt";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filepath),2*1024);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(copyfilepath),2*1024);
        int len;
        while((len = bis.read()) != -1){
            bos.write(len);
        }
        bos.close();
        bis.close();
    }

    /**
     * 利用缓冲流进行拷贝，多个字节多个字节拷贝
     * @throws Exception
     */
    @Test
    public void t1() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : BufferedStreamDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"FileInputStreamDemo.txt";
        String copyfilepath=path+"FileInputStreamDemo--2.txt";

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filepath));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(copyfilepath));
        byte[] b = new byte[2*1024];
        int len;
        while((len = bis.read(b)) != -1){
            bos.write(b, 0, len);
        }
        bos.close();
        bis.close();
    }

    /**
     * 利用缓冲流实现对文件的追加
     * @throws Exception
     */
    @Test
    public void t3() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : BufferedStreamDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"FileInputStreamDemo.txt";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filepath,true));
        bos.write("sadadas".getBytes());
        bos.close();
    }

    /**
     * 利用缓冲流读取文件
     * @throws Exception
     */
    @Test
    public void t5() throws Exception{

        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : BufferedStreamDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"FileInputStreamDemo.txt";
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filepath));
//        FileInputStream fis = new FileInputStream(filepath);
        int len;
        while((len = inputStream.read()) != -1){
            System.out.print((char)len);
        }
        inputStream.close();
    }

}
