package com.github.bjlhx15.common.iostream;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

public class PrintStreamAndWriterDemo {
    /**
     * 使用PrintStream进行打印到文件的操作
     * @throws Exception
     */
    @Test
    public void t1() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : PrintStreamAndWriterDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"PrintStreamDemo.txt";
        PrintStream ps = new PrintStream(new FileOutputStream(filepath));
        ps.print("我是打印流测试(PrintStream)");
        ps.close();
    }

    /**
     * 使用PrintWriter进行打印到文件的操作
     * @throws Exception
     */
    @Test
    public void t2() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : PrintStreamAndWriterDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"PrintStreamDemo.txt";
        PrintWriter pw = new PrintWriter(new FileWriter(filepath));
        pw.write("我是打印流测试(PrintWriter)");
        pw.close();
    }

    /**
     * 使用打印流进行重定向的操作，从文件读取内容到程序
     * @throws Exception
     */
    @Test
    public void t3() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : PrintStreamAndWriterDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"PrintStreamDemo.txt";

        System.setIn(new FileInputStream(filepath));

        Scanner input = new Scanner(System.in);
        String next = input.next();
        System.out.println(next);
        input.close();
    }

    /**
     * 使用打印流进行重定向操作，从程序将内容打印到文件
     * @throws Exception
     */
    @Test
    public void t4() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : PrintStreamAndWriterDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"PrintStreamDemo.txt";

        System.setOut(new PrintStream(filepath));
        System.out.println("我是打印流重定向操作");
    }
}
