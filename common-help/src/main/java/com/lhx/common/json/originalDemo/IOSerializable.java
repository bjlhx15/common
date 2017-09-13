package com.lhx.common.json.originalDemo;

import org.junit.Test;

import java.io.*;

/**
 * 简单的Io-serializable序列化
 */
public class IOSerializable<T> {
    //序列化对象
    public void writeMethod(String fileName, T t) {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            //序列化对象
            objectOutputStream.writeObject(t);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                //关闭输出流
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //反序列对象得到数据
    public T readMethod(String fileName) {
        ObjectInputStream objectInputStream = null;
        T t = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            t = (T) objectInputStream.readObject();

        } catch (IOException E) {
            E.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException E) {
                    E.printStackTrace();
                }
            }
        }
        return t;
    }
    @Test
    public void testWrite(){
        IOSerializable<String> serializable = new IOSerializable<String>();
        serializable.writeMethod("testWrite","我是中国人");
        System.out.println("ok");
    }@Test
    public void testRead(){
        IOSerializable<String> serializable = new IOSerializable<String>();
        String testWrite = serializable.readMethod("testWrite");
        System.out.println(testWrite);
        System.out.println("ok");
    }
}
