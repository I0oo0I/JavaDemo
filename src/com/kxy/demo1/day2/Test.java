package com.kxy.demo1.day2;

import java.util.Random;

public class Test {
	
	private static final long currrentTime = System.currentTimeMillis();
	
	private final int i;
	
	public Test() {
		Random rd = new Random();
		i = rd.nextInt(100); 	//����������final������ÿ�ζ����, �� static final �������ǣ�  final�����ڹ��캯���г�ʼ��
	}
	
	public static void main(String[] args) {
		Random radom = new Random(47);
		System.out.println(radom.nextInt(10));
		System.out.println(radom.nextInt(10));
		
		Student student = new Student("С��", 18, new Score(99, 100));
		System.out.println(student.toString());
		
		StaticStudent ss = StaticStudent.getInstance("С��", 18);
		System.out.println(ss.getName());
		
		PackageStudent ps = new PackageStudent();
		ps.name = "aa";
		System.out.println(currrentTime);
		
		System.out.println(new Test().i);
	}
}
