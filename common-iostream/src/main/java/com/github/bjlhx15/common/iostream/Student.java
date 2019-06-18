package com.github.bjlhx15.common.iostream;


import java.io.Serializable;

/*
 * 为测试对象流创建一个对象
 */
public class Student implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6271405124073931152L;
    private String name;
    private int age;
    private transient String info1;
    private static String info2;

    public Student() {
    }

    public Student(String name, int age, String info1, String info2) {
        super();
        this.name = name;
        this.age = age;
        this.info1 = info1;
        this.info2 = info2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public static String getInfo2() {
        return info2;
    }

    public static void setInfo2(String info2) {
        Student.info2 = info2;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", info1=" + info1 + "]" + ", info2=" + info2 + "]";
    }


}

