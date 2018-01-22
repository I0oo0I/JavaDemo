package com.kxy.demo1.day2.xiancheng.callAble;

import java.util.concurrent.Callable;

/**
 * Runnable 执行独立任务是没有返回值的，run() 方法的返回值是void， 若想有返回值，
 * 可以使用 Callable ，来代替 Runnable
 * @author Administrator
 *
 */
public class TaskWithResult implements Callable<String>{

	private int id;
	
	public TaskWithResult(int id) {
		this.id = id;
	}
	
	@Override
	public String call() throws Exception {
		return "result of TaskWithResult" + id;
	}

}
