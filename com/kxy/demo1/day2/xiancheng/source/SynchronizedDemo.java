package com.kxy.demo1.day2.xiancheng.source;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedDemo implements Runnable{

	public synchronized void doTest1() {
		try {
			System.out.println("�̣߳�" + Thread.currentThread().getName() + "����test1");
			Thread.sleep(1000L);
			System.out.println("�̣߳�" + Thread.currentThread().getName() + "�뿪test1");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void doTest3() {
			System.out.println("�̣߳�" + Thread.currentThread().getName() + "����test3");
			System.out.println("�̣߳�" + Thread.currentThread().getName() + "�뿪test3");
	}

	public synchronized void doTest2() {
		try {
			System.out.println("�̣߳�" + Thread.currentThread().getName() + "����test2");
			Thread.sleep(1000L);
			System.out.println("�̣߳�" + Thread.currentThread().getName() + "�뿪test2");
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
		sy.doTest1();  //exec���߳����˹���� sy��
					   //mian�߳�  sy.doTest3û��ͬ�����������У� syӦ������main�̷߳���doTest1����
		
		
	}
}
