package com.hubenas.entity;

import java.text.MessageFormat;

public class PersonAImpl implements IPerson {
	private String name;
	private int age;
	
	
	@Override
	public void eatSome() {
		System.out.println(MessageFormat.format("名字为:{0},类名为{1}", 
			this.getName(), this.getClass().getName()));
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
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	
}
