package com.kxy.demo1.day2.xiancheng;

import java.io.IOException;

public class ReactiveThreadDemo extends Thread{

	//关于 volatile 关键字, 原理同步， 就是在线程中修改，另外的线程马上可见，不会因不同的线程出现的值不一样
	// https://www.cnblogs.com/zhengbin/p/5654805.html 
	private static volatile double d = 1;
	
	public ReactiveThreadDemo() {
		setDaemon(true);
		start();
	}
	
	@Override
	public void run() {
		//后台一直运行
		while(true) {
			d = d + (Math.PI + Math.E) / d;
			System.out.println("thread d:" + d);
		}
	}
	
	public static void main(String[] args) throws IOException {
		//new UnresponsiveUI();
		
		//后台线程一直在运算d = d + (Math.PI + Math.E) / d， 直到main线程结束
		new ReactiveThreadDemo(); 
		
		System.in.read();
		System.out.println("main d :" + d);
	}
}

class UnresponsiveUI{
	private volatile double d = 1;
	public UnresponsiveUI() throws IOException {
		while (d > 0) {
			d = d + (Math.PI + Math.E) /d ;
			System.out.println("d:" + d);
			System.in.read();
		}
	}
}
