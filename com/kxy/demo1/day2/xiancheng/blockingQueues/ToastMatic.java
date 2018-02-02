package com.kxy.demo1.day2.xiancheng.blockingQueues;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ToastMatic {
	public static void main(String[] args) throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue();
		ToastQueue butteredQueue = new ToastQueue();
		ToastQueue finishedQueue = new ToastQueue();
		
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, butteredQueue));
		exec.execute(new Jammer(butteredQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

class Toast{
	public enum Status{DRY, BUTTERED, JAMMED};
	private Status status = Status.DRY;
	private final int id;
	public Toast(int idn) {id = idn;}
	public void butter() {status = Status.BUTTERED;}
	public void jam() {status = Status.JAMMED;}
	public Status getStatus() {return status;}
	public int getId() {return id;}
	@Override
	public String toString() {
		return "Toast " + id + ": " + status;
	}
}

@SuppressWarnings("serial")
class ToastQueue extends LinkedBlockingQueue<Toast>{}

class Toaster implements Runnable{
	private ToastQueue toastQueue;
	private int count = 0;
	private Random rand = new Random(47);
	public Toaster(ToastQueue tq) {
		toastQueue = tq;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				Toast t = new Toast(count++);
				System.out.println(t);
				toastQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("ToastQueue interrupted");
		}
		System.out.println("Toaser off");
	}
}

class Butterer implements Runnable{
	private ToastQueue dryQueue, butteredQueue;
	public Butterer(ToastQueue dry, ToastQueue buttered) {
		dryQueue = dry;
		butteredQueue = buttered;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Toast t = dryQueue.take();
				t.butter();
				System.out.println(t);
				butteredQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("Butter interrupted");
		}
		System.out.println("Butter off");
	}
}

class Jammer implements Runnable{
	private ToastQueue butteredQueue, finishedQueue;
	public Jammer(ToastQueue butteredQueue, ToastQueue finishedQueue) {
		this.butteredQueue = butteredQueue;
		this.finishedQueue = finishedQueue;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Toast t = butteredQueue.take();
				t.jam();
				System.out.println(t);
				finishedQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("Jammer interrupted");
		}
		System.out.println("Jammer off");
	}
}

class Eater implements Runnable{
	private ToastQueue finishedQueue;
	private int counter;
	public Eater(ToastQueue finishedQueue) {
		this.finishedQueue = finishedQueue;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Toast t = finishedQueue.take();
				if(t.getId() != counter++ || t.getStatus() != Toast.Status.JAMMED) {
					System.out.println("¡·¡·¡·¡·Error£º " + t);
					System.exit(1);
				}else {
					System.out.println("Chomp!" + t);
				}
			}
		} catch (Exception e) {
			System.out.println("Eater interrupted");
		}
		System.out.println("Eater off");
	}
	
}
