package com.kxy.demo1.day2.finalword;

import java.util.Arrays;
import java.util.Random;

public class FinalData {

	private static Random rand = new Random();
	
	private final int i4 = rand.nextInt(20);
	
	//即使设置了 static final, 但是  new Random()，没有设置种子，每一次运行，INT_5的值都会不一样
	static final int INT_5 = rand.nextInt(20);
	
	private final int[] a = {1,2,3,4,5,6};
	
	//public static final int I6;
	
	public final int I7;
	
	public FinalData(){
		//I6 = 100; 在编译时就初始化了
		I7 = 101;
	}
	
	public static void main(String[] args) {
		FinalData fd = new FinalData();
		for(int i = 0; i < fd.a.length; i++) {
			fd.a[i]++;
		}
		//fd.a = new int[] {2}; 无法修改
			//final 限制的是 fd.a, 引用无法改变，但是对引用指向的对象的值可以修改，没有限制
		//fd.i4 = 1;  无法修改
		System.out.println(Arrays.toString(fd.a));
		System.out.println(fd.i4);
		System.out.println(INT_5); 
	}
}
