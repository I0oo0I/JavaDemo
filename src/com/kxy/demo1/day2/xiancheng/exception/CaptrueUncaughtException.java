package com.kxy.demo1.day2.xiancheng.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CaptrueUncaughtException {

	public static void main(String[] args) throws InterruptedException {
		//自定义异常处理
		ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
		exec.execute(new ExceptionThread2());
		exec.shutdown();
		if(!exec.awaitTermination(60L, TimeUnit.SECONDS)) {
			System.out.println("线程池没有关闭");
		}
		
		//线程 run() 抛错的异常无法被上级调用捕捉到，throw抛出的异常会直接到控制台
		ExecutorService exec1 = Executors.newCachedThreadPool();
		exec1.execute(new ExceptionThread2());
		
	}
}
