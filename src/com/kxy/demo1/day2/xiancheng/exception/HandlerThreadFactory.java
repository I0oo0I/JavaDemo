package com.kxy.demo1.day2.xiancheng.exception;

import java.util.concurrent.ThreadFactory;

/**
 * �쳣����factory�����ڴ����̳߳ص�ʱ��ʹ�ã���ÿһ���߳����һ���쳣�������
 * @author Administrator
 *
 */
public class HandlerThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		System.out.println(this + " ������һ���µ��߳�");
		Thread t = new Thread(r);
		System.out.println("�µ��߳��ǣ� " + t);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("δ��׽�쳣 = " + t.getUncaughtExceptionHandler());
		return t;
	}

}
