package com.kxy.demo1.day2.xiancheng;

import java.util.Random;

//定义线程任务
public class LiftOff implements Runnable{

	public static int time = 1;
	protected int countDown = 10;
	private static int taskCount = 0;
	private final int id;
	public LiftOff() {
		time ++;
		Random rd = new Random(time);
		id = rd.nextInt(50);
		System.out.println("0:"+id);
	}
	
	public LiftOff(int countDown) {
		time ++;
		Random rd = new Random(time);
		id = rd.nextInt(50);
		System.out.println("1:"+id);
		this.countDown = countDown;
	}
	
	public String getStatus() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "), "; 
	}
	
	@Override
	public void run() {
		while(countDown-- > 0) {
			System.out.print(getStatus());
			//Thread.yield();的调用是对线程调度器的一种建议，表明：我已经执行完生命周期中最
			//重要的部分了，此刻正是切换给其他任务执行一段时间的大好时机。
			Thread.yield();
		}
		System.out.println("任务结束");
	}

}
