package com.kxy.demo1.day2.xiancheng;

public class JoinThreadDemo {

	public static void main(String[] args) {
		Sleeper sleeper = new Sleeper("sleeper", 1500);
		Sleeper sleeper2 = new Sleeper("sleeper2", 1500);
		
		Joiner joiner = new Joiner("joiner", sleeper);
		Joiner joiner2 = new Joiner("joiner2", sleeper2);
		
		sleeper2.interrupt();
		
		
	}
}

class Sleeper extends Thread{
	private int sleepTime;
	public Sleeper(String name, int sleepTime) {
		super(name);
		this.sleepTime = sleepTime;
		start();
	}
	
	@Override
	public void run() {
		try {
			sleep(sleepTime);
		} catch (InterruptedException e) {
			System.out.println(getName() + " was interruted" + ", isInterrupted:" + isInterrupted());
			return;
		}
		System.out.println(getName() + " awaked");
	}
}

class Joiner extends Thread{
	private Sleeper sleeper;
	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}
	
	@Override
	public void run() {
		try {
			sleeper.join(); //直到sleeper线程执行结束，才会执行joiner线程
		} catch (InterruptedException e) {
			System.out.println(" interrupted");
		}
		System.out.println(getName() + " join completed");
	}
}
