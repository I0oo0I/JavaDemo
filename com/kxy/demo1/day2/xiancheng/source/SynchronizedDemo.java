package com.kxy.demo1.day2.xiancheng.source;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedDemo implements Runnable{

	public synchronized void doTest1() {
		try {
			System.out.println("线程：" + Thread.currentThread().getName() + "进入test1");
			Thread.sleep(1000L);
			System.out.println("线程：" + Thread.currentThread().getName() + "离开test1");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void doTest3() {
			System.out.println("线程：" + Thread.currentThread().getName() + "进入test3");
			System.out.println("线程：" + Thread.currentThread().getName() + "离开test3");
	}

	public synchronized void doTest2() {
		try {
			System.out.println("线程：" + Thread.currentThread().getName() + "进入test2");
			Thread.sleep(1000L);
			System.out.println("线程：" + Thread.currentThread().getName() + "离开test2");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		doTest2();
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SynchronizedDemo sy = new SynchronizedDemo();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sy);
		
		sy.doTest3();  
		sy.doTest1();  //exec的线程锁了共享的 sy，
					   //mian线程  sy.doTest3没有同步，正常进行， sy应被锁，main线程访问doTest1阻塞
		
		
	}
}
