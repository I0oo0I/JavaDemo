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
		System.out.println("线程结束");
	}
}

class Food{
	private boolean isBuy = false;
	public synchronized void eatFood() {
		isBuy = false;
		//notify()
		//唤醒在此对象监视器上等待的单个线程。如果所有线程都在此对象上等待，则会选择唤醒其中一个线程。
		//选择是任意性的，并在对实现做出决定时发生。线程通过调用其中一个 wait 方法，在对象的监视器上等待。 
		//直到当前线程放弃此对象上的锁定，才能继续执行被唤醒的线程。
		//被唤醒的线程将以常规方式与在该对象上主动同步的其他所有线程进行竞争；
		//例如，唤醒的线程在作为锁定此对象的下一个线程方面没有可靠的特权或劣势。 
		//notifyAll() 唤醒在此对象监视器上的所有的等待线程
		notifyAll();
	}
	
	public synchronized void buyFood() {
		isBuy = true;
		notifyAll();
	}
	public synchronized void waitForEating() throws InterruptedException {
		while(isBuy == false) {
			System.out.println("吃--未买");
			wait();
		}
	}
	public synchronized void waitForBuy() throws InterruptedException {
		while(isBuy == true) {
			System.out.println("买>>已买");
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
				System.out.println("吃--操作 !");
				TimeUnit.MILLISECONDS.sleep(200L);
				food.waitForEating();
				System.out.println("吃--准备 !");
				food.eatFood();
				System.out.println("吃--完了!");
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
				System.out.println("买>>操作 !");
				food.waitForBuy();
				System.out.println("买>>准备!");
				food.buyFood();
				System.out.println("买>>成功!");
			}
		}catch (Exception e) {
			System.out.println("Exiting buy interrupt");
		}
		System.out.println("Ending buy");
	}
}