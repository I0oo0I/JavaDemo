package com.kxy.demo1.day2.xiancheng.exception;

import java.io.IOException;

public class Demo2 {

	public static void main(String[] args) {
		Demo demo = new Demo();
		try {
			demo.getNum();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
