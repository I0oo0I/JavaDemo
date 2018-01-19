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
	
	//ͨ����̬���������ض���
	public static StaticStudent getInstance(String name, int age) {
		return new StaticStudent(name, age);
	}
	
	//ͨ����̬�������صĶ��󣬷��ʵ�����Ա
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
}
