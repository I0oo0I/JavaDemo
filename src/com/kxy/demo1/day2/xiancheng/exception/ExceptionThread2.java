package com.kxy.demo1.day2.xiancheng.exception;

public class ExceptionThread2 implements Runnable{

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("��ǰ���е��̣߳� " + t);
		System.out.println("�쳣 = " + t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}

}
