package com.kxy.demo1.day2.xiancheng.source;

public class AboutExecutionSequence {

	public static int k = 0;		//1
	public static AboutExecutionSequence t1 = new AboutExecutionSequence("t1"); //2
	public static AboutExecutionSequence t2 = new AboutExecutionSequence("t2"); //3
	public static int i = print("i");	//4
	public static int n = 99;			//5
	
	public int j = print("j");	 		//执行2或3时，new AboutExecutionSequence
										//这个操作，会初始化  j 构造快   （j和构造块按出现顺序） 构造器
	{
		print("构造快");
	}
	
	static {
		print("静态块");		// 6
	}
	
	public AboutExecutionSequence(String str) {
		System.out.println((++k) + ":" +" 我是" + str);
//		System.out.println((++k) + ":" + str + " i = " +i + " n = " + n);
////		++i;
////		++n;
	}
	
	public static int print(String str) {
		System.out.println((++k) + ":" +" 我是" + str);
		return 1;
//		System.out.println((++k) + ":" + str + " i = " +i + " n = " + n);
//		++n;
//		return ++i;
	}
	
	public static void main(String[] args) {
		AboutExecutionSequence t = new AboutExecutionSequence("init");
	}
}
