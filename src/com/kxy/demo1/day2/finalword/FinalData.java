package com.kxy.demo1.day2.finalword;

import java.util.Arrays;
import java.util.Random;

public class FinalData {

	private static Random rand = new Random();
	
	private final int i4 = rand.nextInt(20);
	
	//��ʹ������ static final, ����  new Random()��û���������ӣ�ÿһ�����У�INT_5��ֵ���᲻һ��
	static final int INT_5 = rand.nextInt(20);
	
	private final int[] a = {1,2,3,4,5,6};
	
	//public static final int I6;
	
	public final int I7;
	
	public FinalData(){
		//I6 = 100; �ڱ���ʱ�ͳ�ʼ����
		I7 = 101;
	}
	
	public static void main(String[] args) {
		FinalData fd = new FinalData();
		for(int i = 0; i < fd.a.length; i++) {
			fd.a[i]++;
		}
		//fd.a = new int[] {2}; �޷��޸�
			//final ���Ƶ��� fd.a, �����޷��ı䣬���Ƕ�����ָ��Ķ����ֵ�����޸ģ�û������
		//fd.i4 = 1;  �޷��޸�
		System.out.println(Arrays.toString(fd.a));
		System.out.println(fd.i4);
		System.out.println(INT_5); 
	}
}
