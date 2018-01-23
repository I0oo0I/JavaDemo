package com.kxy.demo1.day2.xiancheng.exception;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 自己的异常处理类
 * @author Administrator
 *
 */
public class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("未捕捉异常类型：" + e);
	}

}
