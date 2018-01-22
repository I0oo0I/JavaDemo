package com.kxy.demo1.day2.xiancheng;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * java.util.concurrent 包中的 执行器（Executor）管理Thread 对象，简化并发开发
 * Executor 提供一个间接层，由这个中介对象执行任务
 * Executor 允许你管理异步任务的执行，而无须显示地管理线程的生命周期
 * 
 * @author Administrator
 *
 */
public class CacheThreadPool {

	public static void main(String[] args) {
		//创建一个线程池，该线程池根据需要创建新线程，但在它们可用时将重用以前构建的线程。
		//这些池通常会改进执行许多短期异步任务的程序的性能。如果可用，调用执行将重用以前构建的线程。
		//如果没有现有线程可用，将创建一个新线程并将其添加到池中。未使用60秒的线程被终止并从缓存中删除。
		//因此，一个长期闲置的池不会消耗任何资源
		//需要多少线程，创建多少线程
		//线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
		ExecutorService exec  = Executors.newCachedThreadPool();
		
		//创建一个线程池，该线程池重新使用固定数量的线程，这些线程运行在一个共享的无界队列上。
		//在任何时候，大多数nThreads线程都将是主动处理任务。
		//如果在所有线程都处于活动状态时提交了额外的任务，它们将在队列中等待，直到线程可用为止。
		//如果任何线程在关闭之前由于执行失败而终止，则如果需要执行后续任务，则会出现新的线程。
		//池中的线程将一直存在，直到显式关闭为止。
		//ExecutorService exec = Executors.newFixedThreadPool(5);
		
		//单个线程任务，单提交了多个任务时，会排队，相当于  Executors.newFixedThreadPool(1)
		//ExecutorService exec = Executors.newSingleThreadExecutor();
		
		for(int i = 0; i < 5; i ++) {
			exec.execute(new LiftOff());
		}
		//当前线程，会执行 shutdown被 调用之前 所提交的所有任务
		exec.shutdown();
		//后面再加一个，会报错，新增任务，无法执行
		//exec.execute(new LiftOff());
	}
}
