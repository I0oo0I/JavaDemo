package com.kxy.demo1.day2.xiancheng.interrupt;

public class MultiLock {

	public synchronized void f1(int count) {
		if(count-- > 0) {
			System.out.println("f1() 调用  f2(), count = " + count);
			f2(count);
		}
	}
	
	public synchronized void f2(int count) {
		if(count-- > 0) {
			System.out.println("f2() 调用  f1(), count = " + count);
			f1(count);
		}
	}
	
	public static void main(String[] args) {
		final MultiLock multiLock = new MultiLock();
		new Thread() {
			@Override
			public void run() {
				multiLock.f1(10);
			}
		}.start();
	}
}
