package com.kxy.demo1.day2.extendss;

public class Son extends Father{

	private String name;
	
	//不想方法被覆盖的话，可以使用final修饰符
	protected void setNm(String nm) {
		this.name = nm;
	}
	
	public Son() {
		System.out.println("I am Son");
	}
	
	public Son(String name) {
		this.name = name;
	}
	
}
