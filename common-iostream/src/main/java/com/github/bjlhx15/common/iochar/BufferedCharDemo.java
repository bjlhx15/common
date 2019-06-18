package com.github.bjlhx15.common.iochar;

import com.github.bjlhx15.common.iostream.FileOutputStreamDemo;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class BufferedCharDemo {
    /**
     * 进行文件的拷贝
     * @throws Exception
     */
    @Test
    public void t1() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : BufferedCharDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"FileInputStreamDemo.txt";
        String copyfilepath=path+"FileInputStreamDemo--2.txt";
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(copyfilepath));
//		char[] cbuf = new char[1024];
//		int len;
//		while((len=br.read(cbuf)) != -1){
//			bw.write(cbuf, 0, len);
//		}
        //BufferedReader提供了readLine方法，可以不再使用字节读取方式
        String readline;
        while((readline = br.readLine()) != null){
            bw.write(readline);
            bw.newLine();
        }
        bw.close();
        br.close();
    }

    /**
     * 将文件追加到文件
     * @throws Exception
     */

    @Test
    public void t2() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : BufferedCharDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"FileInputStreamDemo.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(filepath,true));
        bw.write("我是测试用例！");
        bw.close();
    }

    /**
     * 读取文件内容
     * @throws Exception
     */
    @Test
    public void t3() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : BufferedCharDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"FileInputStreamDemo.txt";
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String readline;
        while((readline = br.readLine()) != null){
            System.out.println(readline);
        }
        br.close();
    }
}
