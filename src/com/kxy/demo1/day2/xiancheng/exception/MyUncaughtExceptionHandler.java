package com.kxy.demo1.day2.xiancheng.exception;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * �Լ����쳣������
 * @author Administrator
 *
 */
public class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("δ��׽�쳣���ͣ�" + e);
	}

}
