package com.kxy.demo1.day2.xiancheng;

import java.util.concurrent.TimeUnit;

public class NoVisibility {

	//����volatile��Ҳ���˳�while��ѭ��
	private static boolean ready;
	
	private static int number;
	
	private static class ReaderThread extends Thread {

		@Override
		public void run() {
			int i = 0;
			while(!ready) {
			//ȥ��ע��ͬ�����˳���ѭ��
			// Thread.currentThread().yield();
			//ȥ��ע��ͬ�����˳���ѭ��
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
		//����� main read:true ���������С�
		ready = true;
		// System.out.println("after..."+ready);
		System.out.println("main read:"+ready);
		// thread.interrupt();
	}

}
