package com.kxy.demo1.day2.xiancheng;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.kxy.demo1.day2.xiancheng.data.PriorityData;

public class ScheduleDemo {

	public static void main(String[] args) {
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(5);
		exec.schedule(new PriorityData(5, "�߳�1"), 3, TimeUnit.SECONDS);	//�ӳ�3��
		exec.shutdown();
		//List<Runnable> list = exec.shutdownNow(); 	//������ֹ���е��߳�
	}
}
