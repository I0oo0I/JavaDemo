package com.kxy.demo1.day2;

import java.util.Random;

public class Test {
	
	private static final long currrentTime = System.currentTimeMillis();
	
	private final int i;
	
	public Test() {
		Random rd = new Random();
		i = rd.nextInt(100); 	//这样尽管是final，但是每次都会变, 和 static final 的区别是，  final可以在构造函数中初始化
	}
	
	public static void main(String[] args) {
		Random radom = new Random(47);
		System.out.println(radom.nextInt(10));
		System.out.println(radom.nextInt(10));
		
		Student student = new Student("小红", 18, new Score(99, 100));
		System.out.println(student.toString());
		
		StaticStudent ss = StaticStudent.getInstance("小红", 18);
		System.out.println(ss.getName());
		
		PackageStudent ps = new PackageStudent();
		ps.name = "aa";
		System.out.println(currrentTime);
		
		System.out.println(new Test().i);
	}
}
