package com.kxy.demo1.day2.xiancheng.source;

public class SerialNumberGenerator {

	private static volatile int serialNumber = 0;
	
	public static int nextSerialNumber() {
		return serialNumber++;
	}
}
