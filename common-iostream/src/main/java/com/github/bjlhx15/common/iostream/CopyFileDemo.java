package com.github.bjlhx15.common.iostream;

import java.io.*;

/**
 * 推荐使用第四重方式
 */
public class CopyFileDemo {

    static String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
            ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
            : BufferedStreamDemo.class.getClass().getResource("/").getPath();
    static String filepath=path+"apache-maven-3.2.3-bin.tar.gz.txt";
    static String copyfilepath1=path+"FileInputStreamDemo--1.txt";
    static String copyfilepath2=path+"FileInputStreamDemo--2.txt";
    static String copyfilepath3=path+"FileInputStreamDemo--3.txt";
    static String copyfilepath4=path+"FileInputStreamDemo--4.txt";
    //一个字节一个字节的复制，耗时22697毫秒
    public static  void  fun1() throws IOException {
        FileInputStream fis = new FileInputStream(filepath);
        FileOutputStream fos = new FileOutputStream(copyfilepath1);
        int by = 0;
        while ((by=fis.read()) != -1) {
            fos.write(by);
        }
        fis.close();
        fos.close();
    }
    //1024字节数组复制 耗时63毫秒
    public  static void  fun2() throws IOException {
        FileInputStream fis = new FileInputStream(filepath);
        FileOutputStream fos = new FileOutputStream(copyfilepath2);
        int len = 0;
        byte[] bytes =new byte[1024];
        while ((len=fis.read(bytes)) != -1) {
            fos.write(bytes,0,len);
        }
        fis.close();
        fos.close();
    }
    // 一个字节一个字节复制，但是用了缓冲流 耗时64毫秒
    public static   void  fun3() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filepath));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(copyfilepath3));
        int by = 0;
        while ((by=bis.read()) != -1) {
            bos.write(by);
        }
        bis.close();
        bos.close();
    }
    // 1024字节数组复制并用了缓冲流 耗时7毫秒 []
    public  static void  fun4() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filepath));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(copyfilepath4));
        int len = 0;
        byte[] bytes =new byte[1024];
        while ((len=bis.read(bytes)) != -1) {
            bos.write(bytes,0,len);
        }
        bis.close();
        bos.close();
    }

    public static void main(String args[]) throws IOException {
        long t1 = System.currentTimeMillis();
//        fun1();
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        fun2();
        long t3 = System.currentTimeMillis();
        System.out.println(t3-t2);


        fun3();
        long t4 = System.currentTimeMillis();
        System.out.println(t4-t3);


        fun4();
        long t5 = System.currentTimeMillis();
        System.out.println(t5-t4);
    }
}
