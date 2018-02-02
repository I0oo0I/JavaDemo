package com.kxy.demo1.day2.xiancheng.exception;

import java.util.concurrent.ThreadFactory;

/**
 * 异常处理factory，会在创建线程池的时候使用，给每一个线程添加一个异常处理的类
 * @author Administrator
 *
 */
public class HandlerThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		System.out.println(this + " 创建了一个新的线程");
		Thread t = new Thread(r);
		System.out.println("新的线程是： " + t);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("未捕捉异常 = " + t.getUncaughtExceptionHandler());
		return t;
	}

}
