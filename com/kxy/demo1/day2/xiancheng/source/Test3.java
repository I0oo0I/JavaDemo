package com.kxy.demo1.day2.xiancheng.source;

public class Test3 {
	public static void main(String[] args) throws InterruptedException {
		Demo1 demo1 = new Demo1();
		Thread t = new Thread(new Demo2(demo1));
		t.start();
		demo1.getName();
	}
	
}

class Demo1{
	public synchronized void getName() throws InterruptedException {
		if(Thread.currentThread().getName().equals("main")) {
			wait();
		}
		System.out.println("当前线程：" + Thread.currentThread().getName());
		Thread.sleep(2000L);
	}
}

class Demo2 implements Runnable{
	private Demo1 demo1;
	
	public Demo2(Demo1 d) {
		this.demo1 = d;
	}
	
	@Override
	public void run() {
		try {
			demo1.getName();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
