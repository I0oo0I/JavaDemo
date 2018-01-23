package com.kxy.demo1.day2.xiancheng.exception;

public class ExceptionThread2 implements Runnable{

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("当前运行的线程： " + t);
		System.out.println("异常 = " + t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}

}
