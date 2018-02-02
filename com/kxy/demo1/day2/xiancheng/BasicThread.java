package com.kxy.demo1.day2.xiancheng;

public class BasicThread {

	public static void main(String[] args) {
		//将任务绑定到线程
		//start(),执行线程执行的初始化操作
		//执行Runable的run方法，在新线程中启动任务
		Thread t = new Thread(new LiftOff());
		t.start();
		//看执行的结果，start()之后，打印的是  Watting for LiftOff
		//之后出现的是  run() 方法的内容，run方法是由其他线程执行的，
		//main方法的线程，仍然执行了  System.out.println("Watting for LiftOff"); 操作
		//这个能力不仅仅局限在main线程，任意其他线程都可以启动另外一个线程
		System.out.println("Watting for LiftOff"); 
		
		for(int i = 0; i < 5; i++) {
			//循环创建多个线程
			System.out.println();
			new Thread(new LiftOff()).start();
			//执行的结果是随机的，混杂在了一起，说明线程不是一个直接执行完了，这个执行一点，
			//又在另一个线程执行一点。这由线程调度器控制的，若机器上有多个处理器，线程
			//调度器会在这些处理器之间默默的分发线程
		}
	}
}
