package com.kxy.demo1.day2.xiancheng.data;

public class PriorityData implements Runnable{

	private int priority;
	
	private String name;
	
	public PriorityData(int priority, String name) {
		this.priority = priority;
		this.name = name;
	}
	
	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		String threadName = currentThread.getName();
		currentThread.setPriority(priority);
		for(int i = 0; i < 5; i++) {
			System.out.println("线程名称：" + threadName + ",优先级"+currentThread.getPriority()+", 当前名字："+name+"，结果:" + i);
			if(i % 2 != 0) {
				Thread.yield();		//让步，表示这个线程执行的差不多了，可以让别的线程使用cpu了，但是这是一种建议，具体会不会不知道，这个也是带优先级的，相同优先级的
									//可能会先运行，所以yield() 并不是可靠的
			}
		}
	}

}
