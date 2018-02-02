package com.kxy.demo1.day2.xiancheng.criticalSection;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalVariableHolder {
	private static final AtomicInteger threadId = new AtomicInteger(0);
	/**
	 * 这个类提供了线程局部变量。这些变量与正常对应的变量不同，
	 * 因为每个访问一个线程的线程(通过它的get或set方法)都有自己的、独立初始化的变量副本。
	 * ThreadLocal实例通常是类中的私有静态字段，希望将状态与线程关联(例如，用户ID或事务ID)。
	 */
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		private Random rand = new Random(47); //设置了种子以后，对同一个线程，取得值是相同的，对不同的线程取得值是不同的
		
		/**
		 * 返回此线程局部变量的当前线程的“初始值”。线程第一次使用 get() 方法访问变量时将调用此方法，
		 * 但如果线程之前调用了 set(T) 方法，则不会对该线程再调用 initialValue 方法。
		 * 通常，此方法对每个线程最多调用一次，但如果在调用 get() 后又调用了 remove()，则可能再次调用此方法。 
		 * 该实现返回 null；如果程序员希望线程局部变量具有 null 以外的值，则必须为 ThreadLocal 创建子类，并重写此方法。
		 * 通常将使用匿名内部类完成此操作。 
		 */
		protected synchronized Integer initialValue() { 
			//return rand.nextInt(10);  //Random产生的value不一定是不相等的，虽然单个线程的变量都是独立的，但是不同线程之间也可能会产生相同值
			return threadId.getAndIncrement();
		}
		
		
	};
	
	public static void increment() {
		value.set(value.get() + 1);
	}
	
	public static int get() {
		return value.get();
	}
	
	public static void main(String[] args) throws InterruptedException {
//		ExecutorService exec = Executors.newCachedThreadPool();
//		for(int i = 0; i < 5; i++) {
//			exec.execute(new Accessor(i));
//		}
//		TimeUnit.MILLISECONDS.sleep(1L);
//		exec.shutdownNow();
		System.out.println(Thread.currentThread().getName() + ", value : " + value.get());
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++) {
			exec.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ", value : " + value.get());
				}
			});
		}
	}
}

class Accessor implements Runnable{
	private final int id;
	public Accessor(int idn) {
		this.id = idn;
	}
	
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}
	
	@Override
	public String toString() {
		return "#" + id + ": " + ThreadLocalVariableHolder.get();
	} 
}