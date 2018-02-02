package com.kxy.demo1.day2.xiancheng.source;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo implements Runnable{

	private AtomicInteger i = new AtomicInteger(0);
	
	public int getValue() {
		return i.get();
	}
	
	private void evenIncrement() {
		i.addAndGet(2);
	}
	
	@Override
	public void run() {
		while(true) {
			evenIncrement();
		}
	}
	
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.err.println("Aborting(�쳣��ֹ)");
				System.exit(0);
			}
		}, 5000); //5�����ֹ����Ϊ������򲻻���ֹ
		
		AtomicIntegerDemo demo = new AtomicIntegerDemo();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(demo);
		
		while(true) {
			int val = demo.getValue();
			if(val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}

}
