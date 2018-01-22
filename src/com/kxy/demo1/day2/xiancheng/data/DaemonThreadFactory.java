package com.kxy.demo1.day2.xiancheng.data;

import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory{

	/**
	 * ���� Executors �����̵߳� ����̨�����ȼ������ƣ�
	 */
	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setDaemon(true);	//������Ϊ�˺�̨�߳�
		return thread;
	}

}
