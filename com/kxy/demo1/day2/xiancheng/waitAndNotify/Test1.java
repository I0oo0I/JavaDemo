package com.kxy.demo1.day2.xiancheng.waitAndNotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1 {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		DemoData data = new DemoData();
		exec.execute(new Demo2(data));
		exec.execute(new Demo1(data));
		exec.shutdown();
	}
}


class Demo1 implements Runnable{
	private DemoData demoData;
	public Demo1(DemoData data) {
		this.demoData = data;
	}
	
	@Override
	public void run() {
		System.out.println("进入test1");
		while(!demoData.getIsDone()) {
			demoData.test1();
		}
		System.out.println("test1执行完毕");
	}
}

class Demo2 implements Runnable{
	private DemoData demoData;
	
	public Demo2(DemoData data) {
		this.demoData = data;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("进入test2");
//			while(!demoData.getIsDone()) {
//				demoData.test2();
//			}
			demoData.test2();
			System.out.println("test2执行完毕");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class DemoData{
	private volatile boolean isDone = false;
	
	public synchronized void test1() {
		isDone = true;
		System.out.println("进入释放...");
		notify();
	}
	
	public synchronized void test2() throws InterruptedException {
		//从结果来看，无论是notify还是wait，执行之后都要再做一次判断，判断是否继续执行，还是重新挂起，
		//比如 notify石方后，还要在这里判断一次，如果判断结果为false，test2不会继续执行了，线程继续被挂起
		//但是这里的while改成if的话，notify释放后，不会再判断了，这个判断只是在进入的时候判断一次，一旦被释放，
		//被释放的线程继续执行wait之后的代码，这也是为什么会把判断写在while循环中的原因
		while(!getIsDone()) {
			System.out.println("进入阻塞...");
			wait();
		}
	}
	
	public synchronized boolean getIsDone() {
		System.out.println(Thread.currentThread().getName() + ", 进行判断");
		return isDone;
	}
}