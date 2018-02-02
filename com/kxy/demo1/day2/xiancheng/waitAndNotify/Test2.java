package com.kxy.demo1.day2.xiancheng.waitAndNotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test2 {
	ExecutorService exec = Executors.newCachedThreadPool();
	ThreadDemo demo = new ThreadDemo(this);
	
	public Test2() {
		exec.execute(demo);
	}
	public static void main(String[] args) {
		new Test2();
	}
}

class ThreadDemo implements Runnable{
	private Test2 test2;
	
	public ThreadDemo(Test2 t) {
		this.test2 = t;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				for(int i = 0; i < 5; i++) {
					System.out.println(i);
					TimeUnit.MILLISECONDS.sleep(100);
					if(i == 4) {
						test2.exec.shutdownNow();
					}
				}
				TimeUnit.MILLISECONDS.sleep(200);
			}	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
