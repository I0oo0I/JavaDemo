package com.kxy.demo1.day2.xiancheng.waitAndNotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxOMatic {
	public static void main(String[] args) throws InterruptedException {
		Food food = new Food();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new EatFood(food));
		exec.execute(new BuyFood(food));
		TimeUnit.SECONDS.sleep(5L);
		exec.shutdownNow();
		System.out.println("�߳̽���");
	}
}

class Food{
	private boolean isBuy = false;
	public synchronized void eatFood() {
		isBuy = false;
		//notify()
		//�����ڴ˶���������ϵȴ��ĵ����̡߳���������̶߳��ڴ˶����ϵȴ������ѡ��������һ���̡߳�
		//ѡ���������Եģ����ڶ�ʵ����������ʱ�������߳�ͨ����������һ�� wait �������ڶ���ļ������ϵȴ��� 
		//ֱ����ǰ�̷߳����˶����ϵ����������ܼ���ִ�б����ѵ��̡߳�
		//�����ѵ��߳̽��Գ��淽ʽ���ڸö���������ͬ�������������߳̽��о�����
		//���磬���ѵ��߳�����Ϊ�����˶������һ���̷߳���û�пɿ�����Ȩ�����ơ� 
		//notifyAll() �����ڴ˶���������ϵ����еĵȴ��߳�
		notifyAll();
	}
	
	public synchronized void buyFood() {
		isBuy = true;
		notifyAll();
	}
	public synchronized void waitForEating() throws InterruptedException {
		while(isBuy == false) {
			System.out.println("��--δ��");
			wait();
		}
	}
	public synchronized void waitForBuy() throws InterruptedException {
		while(isBuy == true) {
			System.out.println("��>>����");
			wait();
		}
	}
}

class EatFood implements Runnable{
	private Food food;
	public EatFood(Food f) {
		this.food = f;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				System.out.println("��--���� !");
				TimeUnit.MILLISECONDS.sleep(200L);
				food.waitForEating();
				System.out.println("��--׼�� !");
				food.eatFood();
				System.out.println("��--����!");
			}
		}catch (InterruptedException e) {
			System.out.println("Exiting eat interrupt");
		}
		System.out.println("Ending eat");
	}
}

class BuyFood implements Runnable{
	private Food food;
	public BuyFood(Food f) {
		this.food = f;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				System.out.println("��>>���� !");
				food.waitForBuy();
				System.out.println("��>>׼��!");
				food.buyFood();
				System.out.println("��>>�ɹ�!");
			}
		}catch (Exception e) {
			System.out.println("Exiting buy interrupt");
		}
		System.out.println("Ending buy");
	}
}