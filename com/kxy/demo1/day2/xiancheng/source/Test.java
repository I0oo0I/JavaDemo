package com.kxy.demo1.day2.xiancheng.source;

public class Test {

	private int a = 0;
	
	public void doTest(int b) {
		a = b;
	}
	
	public void doTest() {
		System.out.println(a);
	}
	
	public static void main (String[] args) {
		Test test = new Test();
		System.out.println(test.a);
		test.doTest(1);
		System.out.println(test.a);
		test.doTest();
		
		Test test2 = new Test();
		System.out.println(test2.a);
	}
}
