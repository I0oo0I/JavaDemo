package com.kxy.demo1.day2.xiancheng.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Interrupting {
	private static ExecutorService exec = Executors.newCachedThreadPool();
	static void test(Runnable r) throws InterruptedException {
		//Future 表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并获取计算的结果。
		//计算完成后只能使用 get 方法来获取结果，如有必要，计算完成前可以阻塞此方法。
		//取消则由 cancel 方法来执行。还提供了其他方法，以确定任务是正常完成还是被取消了。
		//一旦计算完成，就不能再取消计算。如果为了可取消性而使用 Future 但又不提供可用的结果，
		//则可以声明 Future<?> 形式类型、并返回 null 作为底层任务的结果。 
		Future<?> f = exec.submit(r);
		TimeUnit.MILLISECONDS.sleep(100L);
		System.out.println("阻塞 " + r.getClass().getName());
		//试图取消对此任务的执行。如果任务已完成、或已取消，或者由于某些其他原因而无法取消，则此尝试将失败。
		//当调用 cancel 时，如果调用成功，而此任务尚未启动，则此任务将永不运行。
		//如果任务已经启动，则 mayInterruptIfRunning 参数确定是否应该以试图停止任务的方式来中断执行此任务的线程。 
		f.cancel(true);
		//f.isDone();
	}
	
	public static void main(String[] args) throws Exception {
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3L);
		System.out.println("Aborting with System.exit(0)");
		System.exit(0);
		//结果分析，只用 SleepBlocked 打印了 退出 SleepBlocked.run()，说明只有sleep阻塞是可以中断的
		//而 I/O 和 同步阻塞不可中断， 所以这两种不需要 InterruptedException 处理器
		//特别是对于I/O 有可能锁住你的多线程任务的可能
	}
}

/**
 * sleep阻塞
 */
class SleepBlocked implements Runnable{
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100L);
		} catch (Exception e) {
			System.out.println("Sleep阻塞异常");
		}
		System.out.println("退出 SleepBlocked.run()");
	}
}

/**
 * I/O 阻塞
 */
class IOBlocked implements Runnable{
	private InputStream in;
	public IOBlocked(InputStream is) {
		in = is;
	}
	@Override
	public void run() {
		try {
			System.out.println("等待  read():");
			in.read();
		} catch (IOException e) {
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("I/O 阻塞");
			}else {
				throw new RuntimeException(e);
			}
		}
		System.out.println("退出 IOBlocked.run()");
	}
}

/**
 * 同步锁阻塞
 */
class SynchronizedBlocked implements Runnable{
	public synchronized void f() {
		while(true) { 	//f()永远被阻塞
			Thread.yield();
		}
	}
	public SynchronizedBlocked() {
		new Thread() {
			@Override
			public void run() {
				f();
			}
		};
	}
	@Override
	public void run() {
		System.out.println("尝试 call f()");
		f();
		System.out.println("退出 SynchronizedBlocked.run()");
	}
	
}