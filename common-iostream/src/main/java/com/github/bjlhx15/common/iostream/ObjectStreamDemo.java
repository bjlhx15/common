package com.github.bjlhx15.common.iostream;

import org.junit.Test;

import java.io.*;

public class ObjectStreamDemo {
    /**
     * 写入对象
     *
     * @throws Exception
     */
    @Test
    public void t1() throws Exception {
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : ObjectStreamDemo.class.getClass().getResource("/").getPath();
        String filepath = path + "objecttest.txt";

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath));
        //当然上述可以 改成增加缓冲流------------------
//        构建缓冲流
//        InputStream inputStream=new FileInputStream(filepath);
//        //构建缓冲流
//        BufferedInputStream buf=new BufferedInputStream(inputStream);
//        //构建字符输入的对象流
//        ObjectInputStream ois=new ObjectInputStream(buf);
        oos.writeInt(10);
        oos.writeObject(new String("测试1"));
        oos.close();
    }

    /**
     * 读取对象
     *
     * @throws Exception
     */
    @Test
    public void t2() throws Exception {

        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : ObjectStreamDemo.class.getClass().getResource("/").getPath();
        String filepath = path + "objecttest.txt";

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));
        //当然上述可以 改成增加缓冲流------------------
//        构建缓冲流
//        InputStream inputStream=new FileInputStream(filepath);
//        //构建缓冲流
//        BufferedInputStream buf=new BufferedInputStream(inputStream);
//        //构建字符输入的对象流
//        ObjectInputStream ois=new ObjectInputStream(buf);
        int readint = ois.readInt();
        Object obj = ois.readObject();
        System.out.println(readint);
        System.out.println(obj);
        ois.close();
    }

    /**
     * 写入一个Student对象
     *
     * @throws Exception
     */
    @Test
    public void t3() throws Exception {

        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : ObjectStreamDemo.class.getClass().getResource("/").getPath();
        String filepath = path + "objecttest.txt";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath));
        //当然上述可以 改成增加缓冲流------------------
//        构建缓冲流
//        InputStream inputStream=new FileInputStream(filepath);
//        //构建缓冲流
//        BufferedInputStream buf=new BufferedInputStream(inputStream);
//        //构建字符输入的对象流
//        ObjectInputStream ois=new ObjectInputStream(buf);
        oos.writeObject(new Student("测试", 18, "信息1", "信息2"));
        oos.close();
    }

    /**
     * 读取对象
     *
     * @throws Exception
     */
    @Test
    public void t4() throws Exception {
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : ObjectStreamDemo.class.getClass().getResource("/").getPath();
        String filepath = path + "objecttest.txt";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));
        //当然上述可以 改成增加缓冲流------------------
//        构建缓冲流
//        InputStream inputStream=new FileInputStream(filepath);
//        //构建缓冲流
//        BufferedInputStream buf=new BufferedInputStream(inputStream);
//        //构建字符输入的对象流
//        ObjectInputStream ois=new ObjectInputStream(buf);


        Student stu = (Student) ois.readObject();
        System.out.println(stu);
        ois.close();
    }
}
