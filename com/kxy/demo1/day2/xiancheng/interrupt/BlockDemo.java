package com.kxy.demo1.day2.xiancheng.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockDemo {
	public static void main(String[] args) throws InterruptedException {
		//在main线程中 执行了 new Blocked2() 操作， BlockedMutex对象被main线程锁住
		//t线程的run方法，访问被锁的 BlockedMutex对象的 f()方法，无法获取到锁，程序到blocked.f();一直阻塞
		Thread t = new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("线程t中断");
		t.interrupt(); // interrupt可以打断被互斥而阻塞的调用
	}
}

class BlockedMutex{
	private Lock lock = new ReentrantLock(); //ReentrantLock上阻塞的任务可以被打断
	public BlockedMutex(){
		System.out.println("当前线程：" + Thread.currentThread().getName());
		lock.lock();
	}
	
	public void f() {
		try {
//			如果当前线程未被中断，则获取锁。 
//			如果锁可用，则获取锁，并立即返回。 
//			如果锁不可用，出于线程调度目的，将禁用当前线程，并且在发生以下两种情况之一以前，该线程将一直处于休眠状态： 
//				锁由当前线程获得；
//				或者其他某个线程中断当前线程，并且支持对锁获取的中断。 
//			如果当前线程： 
//				在进入此方法时已经设置了该线程的中断状态；
//				或者在获取锁时被中断，并且支持对锁获取的中断， 
//			则将抛出 InterruptedException，并清除当前线程的已中断状态。
			lock.lockInterruptibly();
			System.out.println("f()获取到锁");
		} catch (InterruptedException e) {
			System.out.println("中断f()的锁的获取");
		}
	}
}

class Blocked2 implements Runnable{
	BlockedMutex blocked = new BlockedMutex();
	@Override
	public void run() {
		System.out.println("等待f()调用");
		blocked.f();
		System.out.println("中断呼叫");
	}
}
