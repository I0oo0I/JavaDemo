package com.kxy.demo1.day2.xiancheng.source;

public class  EventGenerator extends IntGenerator{

	private  int currentEvenValue = 0;
	
	/**
	 * synchronized  有没有这个关键字，结果是不一样的
	 */
	@Override
	public synchronized int next() {
		++currentEvenValue;  //非原子操作，该过程包含三个原子操作， currentEvenValue = currentEvenValue + 1
							// 读取  currentEvenValue  currentEvenValue+1   赋值给currentEvenValue
							// 单个操作线程不会中断，但是这三个原子操作之间会被线程中断挂起，这也是没有锁会出现问题原因
		Thread.yield(); 	// 加快线程的切换
							// https://www.cnblogs.com/huanmin/p/6156192.html,
							// http://blog.csdn.net/gds2014/article/details/50317145
		return currentEvenValue;
	}
	
	public static void main(String[] args) {
		EvenChecker.test(new EventGenerator());
	}

}
