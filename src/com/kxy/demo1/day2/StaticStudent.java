package com.kxy.demo1.day2;

public class StaticStudent {

	private String name;
	
	private int age;
	
	private StaticStudent() {
		
	}
	
	private StaticStudent(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	//通过静态方法，返回对象
	public static StaticStudent getInstance(String name, int age) {
		return new StaticStudent(name, age);
	}
	
	//通过静态方法返回的对象，访问单个成员
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
}
