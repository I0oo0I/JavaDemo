package com.kxy.demo1.day2.xiancheng.callAble;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		
		List<Future<String>> results = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			//另外一个线程，执行线程任务，返回一个结果
			results.add(exec.submit(new TaskWithResult(i)));
		}
		//main() 方法线程继续执行，fs.isDone,判断检查结果是否已经准备就绪，
		//没有的话，fs.get()直接调用会阻塞
		System.out.println("我是main()线程,继续执行");
		for(Future<String> fs : results) {
			try {
				if(fs.isDone()) {
					System.out.println(fs.get());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}
