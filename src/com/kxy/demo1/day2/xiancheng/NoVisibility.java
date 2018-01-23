package com.kxy.demo1.day2.xiancheng;

import java.util.concurrent.TimeUnit;

public class NoVisibility {

	//加上volatile后也会退出while死循环
	private static boolean ready;
	
	private static int number;
	
	private static class ReaderThread extends Thread {

		@Override
		public void run() {
			int i = 0;
			while(!ready) {
			//去掉注释同样会退出死循环
			// Thread.currentThread().yield();
			//去掉注释同样会退出死循环
			System.out.println(i++);
			System.out.println("run ready:"+ready);
		}
		System.out.println(number);
	
	}

	}

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new ReaderThread();
		thread.start();
		//TimeUnit.SECONDS.sleep(10);
		number = 42;
		// System.out.println("before..."+ready);
		//输出了 main read:true 程序还在运行。
		ready = true;
		// System.out.println("after..."+ready);
		System.out.println("main read:"+ready);
		// thread.interrupt();
	}

}
