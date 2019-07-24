package com.github.bjlhx15.common.help.poi;

import java.util.Date;

public class Person {
    private String name;
    private Integer age;
    private String gender;
    private Date date;

    public Person() {
    }

    public Person(String n, int a, String g) {
        name = n;
        age = a;
        gender = g;
    }

    public Person(String n, int a, String g, Date d) {
        name = n;
        age = a;
        gender = g;
        date = d;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", date=" + date +
                '}';
    }
}