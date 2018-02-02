package com.kxy.demo1.day2.xiancheng.exception;

public class ExceptionThread implements Runnable{

	@Override
	public void run() {
		throw new RuntimeException();
	}

}
