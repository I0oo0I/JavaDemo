package com.kxy.demo1.day2.xiancheng;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.kxy.demo1.day2.xiancheng.data.PriorityData;

/**
 * 线程优先级
 * @author Administrator
 *
 */
public class ThreadPriority {

	public static void main(String[] args) {
		//ExecutorService exec = Executors.newCachedThreadPool();
		ExecutorService exec = Executors.newSingleThreadExecutor();
		exec.execute(new PriorityData(1, "线程2"));
		exec.execute(new PriorityData(5, "线程1"));
		exec.shutdown();
		//从运行的结果来看，并不是优先级高的，就一定会优先运行，线程应该是随机的，只是优先级高的先运行的概率要大一些
	}
}
