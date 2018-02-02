package com.kxy.demo1.day2.xiancheng;

import java.util.Random;

//�����߳�����
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
			//Thread.yield();�ĵ����Ƕ��̵߳�������һ�ֽ��飬���������Ѿ�ִ����������������
			//��Ҫ�Ĳ����ˣ��˿������л�����������ִ��һ��ʱ��Ĵ��ʱ����
			Thread.yield();
		}
		System.out.println("�������");
	}

}
