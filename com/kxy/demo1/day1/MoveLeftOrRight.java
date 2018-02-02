package com.kxy.demo1.day1;

public class MoveLeftOrRight {

	public static void main(String[] args) {
		int a = 0xffff;
		System.out.println(a);
		System.out.println(Integer.toBinaryString(a));
		System.out.println("1111111111111111".length()); //实际上总共是32为，高位补16个0
		//现在左移10位
		int b = a << 10;
		System.out.println(b);
		System.out.println(Integer.toBinaryString(b));
		System.out.println("11111111111111110000000000".length()); //是26位，低位补10个0
	}
}
