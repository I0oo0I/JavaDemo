package com.kxy.demo1.day2.extendss;

public class Son extends Father{

	private String name;
	
	//���뷽�������ǵĻ�������ʹ��final���η�
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
