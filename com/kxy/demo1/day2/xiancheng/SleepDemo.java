package com.kxy.demo1.day2.xiancheng;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.kxy.demo1.day2.xiancheng.data.SleepData;

public class SleepDemo {

	public static void main(String[] args) {
		//�����߳��������
		//ExecutorService exec = Executors.newSingleThreadExecutor();
		//ExecutorService exec = Executors.newCachedThreadPool(); // �����ѭ�� 10 �����񣬽���10���߳�
		ExecutorService exec = Executors.newFixedThreadPool(5);	  // ������ 5 ���߳�
		for(int i = 0; i < 10; i++) {
			exec.execute(new SleepData());
		}
		exec.shutdown();
	}
}
