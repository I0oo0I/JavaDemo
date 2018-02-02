
package com.kxy.demo1.day2.xiancheng.data;

import java.util.concurrent.TimeUnit;

public class SimpleDaemonData implements Runnable{

	@Override
	public void run() {
		try {
			while(true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException e) {
			System.out.println("sleep() interrupted");
		}
	}

}
