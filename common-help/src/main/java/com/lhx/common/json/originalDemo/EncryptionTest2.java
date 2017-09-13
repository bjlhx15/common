package com.lhx.common.json.originalDemo;

import java.io.*;

public class EncryptionTest2 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String password = "pass";
    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @org.junit.Test
    public void testTwoStore() throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("result.obj"));
        EncryptionTest2 test = new EncryptionTest2();
        //试图将对象两次写入文件
        out.writeObject(test);
        out.flush();
        System.out.println(new File("result.obj").length());
        out.writeObject(test);
        out.close();
        System.out.println(new File("result.obj").length());

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                "result.obj"));
        //从文件依次读出两个文件
        EncryptionTest2 t1 = (EncryptionTest2) oin.readObject();
        EncryptionTest2 t2 = (EncryptionTest2) oin.readObject();
        oin.close();

        //判断两个引用是否指向同一个对象
        System.out.println(t1 == t2);
    }

    @org.junit.Test
    public void testTwoStore2() throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("testTwoStore2.obj"));
        EncryptionTest2 test = new EncryptionTest2();
        test.i = 1;
        out.writeObject(test);
        out.flush();
        test.i = 2;
        out.writeObject(test);
        out.close();
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                "testTwoStore2.obj"));
        EncryptionTest2 t1 = (EncryptionTest2) oin.readObject();
        EncryptionTest2 t2 = (EncryptionTest2) oin.readObject();
        System.out.println(t1.i);
        System.out.println(t2.i);
    }
}
