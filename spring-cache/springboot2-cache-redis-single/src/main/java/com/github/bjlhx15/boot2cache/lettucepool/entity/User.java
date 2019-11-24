package com.github.bjlhx15.boot2cache.lettucepool.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private String name;
	private int age;
	private Date birthDate;

	public User() {
		super();
	}

	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public User(String name, int age, Date birthDate) {
		this.name = name;
		this.age = age;
		this.birthDate = birthDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}
