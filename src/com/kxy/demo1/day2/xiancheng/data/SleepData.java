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
			//�쳣���ܿ��̣߳����ݵ�����һ���̣߳������run�����в�׽
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.println("�߳�����"+threadName+",��������������" + time + "��");
		}
	}

}
