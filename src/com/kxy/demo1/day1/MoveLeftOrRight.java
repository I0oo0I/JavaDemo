package com.kxy.demo1.day1;

public class MoveLeftOrRight {

	public static void main(String[] args) {
		int a = 0xffff;
		System.out.println(a);
		System.out.println(Integer.toBinaryString(a));
		System.out.println("1111111111111111".length()); //ʵ�����ܹ���32Ϊ����λ��16��0
		//��������10λ
		int b = a << 10;
		System.out.println(b);
		System.out.println(Integer.toBinaryString(b));
		System.out.println("11111111111111110000000000".length()); //��26λ����λ��10��0
	}
}
