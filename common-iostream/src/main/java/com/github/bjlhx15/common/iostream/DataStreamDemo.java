package com.github.bjlhx15.common.iostream;

import org.junit.Test;

import java.io.*;

public class DataStreamDemo {

    String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
            ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
            : DataStreamDemo.class.getClass().getResource("/").getPath();
    String filepath = path + "DataStreamDemo.txt";
    String copyfilepath = path + "DataStreamDemo--2.txt";

    @Test
    public void writeUTF() throws Exception {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(filepath));

        dos.writeUTF("你好,ddddd");//修改版的UTF-8只能用.readUTF读取，其他方法会读取失败

        dos.close();

    }
    @Test
    public void readUTF() throws Exception {
        DataInputStream dis = new DataInputStream(new FileInputStream(filepath));

        String s = dis.readUTF();

        System.out.println(s);
        dis.close();

    }


    @Test
    public void writeData() throws Exception {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(filepath));//直接写入基本数据类型
        dos.writeInt(234);
        dos.writeBoolean(true);
        dos.writeDouble(9887.543);

        dos.close();
    }
    @Test
    public void readData() throws Exception {
        DataInputStream dis = new DataInputStream(new FileInputStream(filepath));//直接读取基本数据类型，不需要存入数组再强转为字符串
        int num = dis.readInt();
        boolean b = dis.readBoolean();
        double d = dis.readDouble();

        System.out.println("num=" + num);
        System.out.println("b=" + b);
        System.out.println("d=" + d);

        dis.close();
    }
}
