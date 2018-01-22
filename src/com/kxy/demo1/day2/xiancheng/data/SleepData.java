package com.kxy.demo1.day2.xiancheng.data;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SleepData implements Runnable{

	@Override
	public void run() {
		Random rd = new Random();
		int time = 0;
		String threadName = Thread.currentThread().getName();
		do {
			time = rd.nextInt(10);
		} while (time == 0);
		try {
			//异常不能跨线程，传递到另外一个线程，因此在run方法中捕捉
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.println("线程名称"+threadName+",本次运行休眠了" + time + "秒");
		}
	}

}
