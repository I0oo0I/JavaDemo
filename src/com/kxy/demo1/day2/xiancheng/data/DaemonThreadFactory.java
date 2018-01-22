package com.kxy.demo1.day2.xiancheng.data;

import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory{

	/**
	 * 设置 Executors 定制线程的 （后台，优先级，名称）
	 */
	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setDaemon(true);	//都设置为了后台线程
		return thread;
	}

}
