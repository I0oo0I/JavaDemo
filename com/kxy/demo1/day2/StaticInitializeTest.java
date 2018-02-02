package com.kxy.demo1.day2;

public class StaticInitializeTest {
	
	public static String name = "Ð¡»¨";
	
	public static int age;
	
	static {
		age = 18;
	}
	
	public static void printStaticField() {
		System.out.println(StaticInitializeTest.name);
		System.out.println(StaticInitializeTest.age);
	}
	
	public static void main(String[] args) {
		printStaticField();
	}
}
