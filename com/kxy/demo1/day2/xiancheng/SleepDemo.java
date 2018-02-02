package com.kxy.demo1.day2.xiancheng;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.kxy.demo1.day2.xiancheng.data.SleepData;

public class SleepDemo {

	public static void main(String[] args) {
		//单个线程排序进行
		//ExecutorService exec = Executors.newSingleThreadExecutor();
		//ExecutorService exec = Executors.newCachedThreadPool(); // 下面的循环 10 个任务，将有10个线程
		ExecutorService exec = Executors.newFixedThreadPool(5);	  // 设置了 5 个线程
		for(int i = 0; i < 10; i++) {
			exec.execute(new SleepData());
		}
		exec.shutdown();
	}
}
