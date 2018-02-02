package com.kxy.demo1.day2.xiancheng.criticalSection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁方法和方法内同步控制块的比较
 * @author Administrator
 *
 */
public class CircleLockSection {
	static void testApprocaches(LockPairManager pman1, LockPairManager pman2) {
		ExecutorService exec = Executors.newCachedThreadPool();
		LockPairManipulator pm1 = new LockPairManipulator(pman1);
		LockPairManipulator pm2 = new LockPairManipulator(pman2);
		
		LockPairChecker pcheck1 = new LockPairChecker(pman1);
		LockPairChecker	pcheck2 = new LockPairChecker(pman2);
		
		exec.execute(pm1);  //线程执行, 对象pm1被锁，pcheck1访问pm1被阻塞， pcheck1通过计数，反映pm1同步方法执行的时间
		exec.execute(pm2);  //线程执行, 对象pm2被锁，pcheck2访问pm2被阻塞， pcheck2通过计数，反映pm2同步方法执行的时间
		exec.execute(pcheck1);
		exec.execute(pcheck2);
		exec.shutdown();
		
		try {
			TimeUnit.MILLISECONDS.sleep(500L);
			
			List<Pair1> list = pman1.storage;
			List<String> valEquals = new ArrayList<>();
			List<String> valNotEquals = new ArrayList<>();
			for(Pair1 p : list) {
				if(p.getX() == p.getY()) {
					valEquals.add(p.getX() + " == " + p.getY());
				}else {
					valNotEquals.add(p.getX() + " != " + p.getY());
				}
			}
			System.out.println("-----------------------不等式-----------------------");
			if(valNotEquals.size() > 0) {
				for(String s : valNotEquals) {
					System.out.println(s);
				}
			}else {
				System.out.println("没有不相等的数据");
			}
			
			System.out.println("-----------------------等式-----------------------");
			if(valEquals.size() > 0) {
				for(String s : valEquals) {
					System.out.println(s);
				}
			}
			
		} catch (Exception e) {
			System.out.println("Sleep interrupted");
		}
		
		System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
		System.exit(0);
	}
	
	public static void main(String[] args) {
		LockPairManager pman1 = new LockPairManager1();
		LockPairManager pman2 = new LockPairManager2();
		testApprocaches(pman1, pman2);
		//pm1: Pair: X:18, y:18 checkCounter = 106
		//pm2: Pair: X:19, y:19 checkCounter = 47561537
		//结果分析，直接在方法上加 synchronized锁对象的时间更长，
		//而在方法内使用同步控制块锁对象的时间要短，这是为什么使用同步控制块的原因：在线程安全的情况下，使其他的线程尽可能多的访问
		
	}
}

class Pair1{
	private int  x,y;
	public Pair1(int x, int y) { //Pair非线程安全的，如果别人给你一个非线程安全的类，怎么办，所以要构造一个线程安全的环境使用他
		this.x = x;
		this.y = y;
	}
	public Pair1() {
		this(0,0);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void incrementX() {
		x++;
	}
	public void incrementY() {
		y++;
	}
	public String toString() {
		return "X:" + x + ", y:" + y;
	}
	
	@SuppressWarnings("serial")
	public class PairValuesNotEqualException extends RuntimeException{
		public PairValuesNotEqualException() {
			super("Pair1 values not equal:" + Pair1.this);
		}
	}
	public void checkState() {
		if(x != y) {
			throw new PairValuesNotEqualException();
		}
	}
}

/**
 * 构造线程安全的环境使用类 Pair
 * @author Administrator
 *
 */
abstract class LockPairManager{
	AtomicInteger checkCounter = new AtomicInteger(0); 
	protected Pair1 p = new Pair1();
	public List<Pair1> storage = Collections.synchronizedList(new ArrayList<Pair1>());
	
	//通过给与的不安全的对象，重新构建一个线程安全的对象，存储时防止，p.getX(), 和 p.getY()，取到不一样的值
	public synchronized Pair1 getPair() {
		return new Pair1(p.getX(), p.getY());
	}
	
	public synchronized void testXY() {
		Pair1 pair = getPair();
		if(pair.getX() != pair.getY()) {
			throw new RuntimeException("不相等");
		}
	}
	
	//store方法是将一个 Pair 对象放入到 synchronized list中，这里不需要添加
	//synchronized
	protected void store(Pair1 p) {
		storage.add(p);
		try {
			TimeUnit.MILLISECONDS.sleep(50L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//将 Pair 的不安全的操作，放在一个线程安全的方法中
	public abstract void increment();
}

class LockPairManager1 extends LockPairManager{
	private Lock lock = new ReentrantLock();
	@Override
	public void increment() {
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			store(getPair());
		} finally {
			lock.unlock();
		}
	}
}

class LockPairManager2 extends LockPairManager{
	private Lock lock = new ReentrantLock();
	@Override
	public void increment() {
//		Pair1 temp;
//		synchronized (this) {
//			p.incrementX();
//			p.incrementY();
//			temp = getPair();
//		}
//		store(temp);
		Pair1 temp;
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		} finally {
			lock.unlock();
		}
		store(temp);
	}
}

class LockPairManipulator implements Runnable{
	private LockPairManager pm;
	public LockPairManipulator(LockPairManager pm) {
		this.pm = pm;
	}
	@Override
	public void run() {
		while(true) {
			pm.increment();
		}
	}
	public String toString() {
		return "Pair1: " + pm.getPair() + " checkCounter = " + pm.checkCounter.get();
	}
}

class LockPairChecker implements Runnable{
	private LockPairManager pm;
	public LockPairChecker(LockPairManager pm) {
		this.pm = pm;
	}
	@Override
	public void run() {
		while(true) {
			pm.checkCounter.incrementAndGet();
			pm.getPair().checkState();
		}
	}
}