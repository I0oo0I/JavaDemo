package com.kxy.demo1.day2.xiancheng;

import java.util.concurrent.TimeUnit;

public class ThreadVarious {

	public static void main(String[] args) {
		new InnerThread1("Inner Thread 1");
		new InnerThread2("Inner Thread 2");
		new InnerRunable1("Inner Runable 1");
		new InnerRunnable2("Inner Runnable 2");
		new ThreadMethod("Thread Method").runTask();
	}
}

class InnerThread1{
	private int countDown = 5;
	private Inner inner;
	private class Inner extends Thread{
		Inner(String name){
			super(name);
			start();
		}
		
		@Override
		public void run() {
			try {
				while(true) {
					System.out.println(this);
					if(--countDown == 0) {
						return;
					}
					sleep(10);
				}
			} catch (InterruptedException e) {
				System.out.println("interrupt");
			}
		}
		
		@Override
		public String toString() {
			return getName() + ": " + countDown;
		}
	}
	
	public InnerThread1(String name) {
		inner = new Inner(name);
	}
}

class InnerThread2{
	private int countDown = 5;
	private Thread t;
	public InnerThread2(String name) {
		t = new Thread(name) {
			@Override
			public void run() {
				try {
					while(true) {
						System.out.println(this);
						if(--countDown == 0) {
							return;
						}
						sleep(10);
					}
				} catch (Exception e) {
					System.out.println("interrupt");
				}
			}
			@Override
			public String toString() {
				return getName() + ": " + countDown;
			}
		};
		t.start();
	}
}

class InnerRunable1{
	private int countDown = 5;
	private Inner inner;
	private class Inner implements Runnable{
		Thread t;
		Inner(String name){
			t = new Thread(this, name);
			t.start();
		}
		
		@Override
		public void run() {
			try {
				while(true) {
					System.out.println(this);
					if(--countDown == 0) {
						return;
					}
					TimeUnit.MILLISECONDS.sleep(10);
				}
			} catch (Exception e) {
				System.out.println("interrut");
			}
		}
		
		@Override
		public String toString() {
			return t.getName() + ": " + countDown;
		}
	}
	
	public InnerRunable1(String name) {
		inner = new Inner(name);
	}
}

class InnerRunnable2 {
	private int countDown = 5;
	private Thread t;
	public InnerRunnable2(String name) {
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(true) {
						System.out.println(this);
						if(--countDown == 0) {
							return ;
						}
						TimeUnit.MILLISECONDS.sleep(10);
					}
				} catch (Exception e) {
					System.out.println("interrupt");
				}
			}
			
			@Override
			public String toString() {
				return t.getName() + ": " + countDown;
			}
		}, name);
		t.start();
	}
}

class ThreadMethod{
	private int countDown = 5;
	private Thread t;
	private String name;
	public ThreadMethod(String name) {
		this.name = name;
	}
	
	public void runTask() {
		if(t == null) {
			t = new Thread(name) {
				@Override
				public void run() {
					try {
						while(true) {
							System.out.println(this);
							if(--countDown == 0) {
								return;
							}
							sleep(10);
						}
					} catch (Exception e) {
						System.out.println("interrupt");
					}
				}
				
				@Override
				public String toString() {
					return getName() + ": " + countDown;
				}
			};
			t.start();
		}
	}
}