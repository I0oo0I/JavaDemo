package com.kxy.demo1.day2.xiancheng.source;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AtomicDemo implements Runnable{

	private volatile int i = 0;
	
	public synchronized int getValue() {
		return i;
	}
	
	/**
	 * synchronized, 保证的是，只能被同一线程访问，加锁，即使这个线程执行的时间到了，
	 * 线程被挂起，下一次还是接着执行， 并不是说这个操作会一定执行完
	 */
	private synchronized void evenIncrement() {
		try {
			System.out.println("当前线程" + Thread.currentThread().getName() + ", i = " + i);
			i++;
			TimeUnit.SECONDS.sleep(1L);
			i++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//i = i + 2;
	}
	
	@Override
	public void run() {
		while(true) {
			evenIncrement();
			//System.out.println("true");
		}
	}
	
	/**
		要明白两个问题，1.锁的对象是谁，2.谁持有了锁。
		假设方法A和B是在同一个类Test中的两个方法。Test t=new Test(); t.methodB();
		这个时候，methodB方法被调用时，因为加了synchronized ，需要先获得一个锁，这个锁的对象应该是t，
		也就是当前的这个Test类的实例，而获得锁的东西是线程，也就是说当前线程拿到了t的锁（而不是你说的B方法获得锁），
		这个时候B方法内调用methodA，因为A也加了synchronized，也需要获得一个锁，
		因为A和B都是Test类中的方法，所以当前线程要获得的锁的对象也是t。
		由于当前线程在执行B方法时已经持有了t对象的锁，因此这时候调用methodA是没有任何影响的，
		相当于方法A上没有加synchronized。
		另一种情况：假设现在有两个Test类Test t1=new Test(); Test t2=new Test(); t1.methodB();
			此时当前线程持有了t1对象的锁t2.methodB();
			此时当前线程也持有了t2对象的锁当前线程持有了两把锁，锁的对象分别是两个不同的Test类的实例t1和t2，
			互相没有影响。
		再一种情况：假设在多线程环境下，两个线程都可以访问Test t=new Test(); 
			此时假设thread1里调用t.methodB();
			同时thread2里调用t.methodB()这时假设thread1先抢到t对象的锁,
			那么thread2需要等待thread1释放t对象的锁才可以执行B方法。
			结果像这样：thread1获得t的锁--thread1执行methodB--thread1执行methodA--释放t的锁---
					 thread2获得t的锁--thread2执行methodB--thread2执行methodA--释放t的锁。
			synchronized还有很多种使用方法，但只有明白是那条线程获得哪个对象的锁，就很容易明白了。
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicDemo demo = new AtomicDemo();
		exec.execute(demo);
		
		//这里包含两个线程，main线程和 AtomicDemo 线程
		//main线程循环判断奇数，并打印奇数，结束程序
		//AtomicDemo 线程循环递增偶数
		//主要是 i++, i++的中间值被读到了，
		//当getValue()使用synchronized, 为什么会正常，不会出现奇数了？？？？
		// AtomicDemo demo = new AtomicDemo(); demo两个线程是共享的同一个
		// exec生成的线程执行 evenIncrement时，锁住了对象 demo， 所有的同步方法被锁
		// main线程 访问 demo.getValue(); 被阻塞，只用exec线程访问完，main线程才能获取
		// demo对象的锁，执行  demo.getValue()操作，因此不会出现线程同步问题
		while(true) {
			int val = demo.getValue();
			System.out.println("当前线程" + Thread.currentThread().getName() + ", val = " + val);
			if(val > 100 && val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}

}
