package com.kxy.demo1.day2.xiancheng.criticalSection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 锁方法和方法内同步控制块的比较
 * @author Administrator
 *
 */
public class CriticalSection {
	static void testApprocaches(PairManager pman1, PairManager pman2) {
		ExecutorService exec = Executors.newCachedThreadPool();
		PairManipulator pm1 = new PairManipulator(pman1);
		PairManipulator pm2 = new PairManipulator(pman2);
		
		PairChecker pcheck1 = new PairChecker(pman1);
		PairChecker	pcheck2 = new PairChecker(pman2);
		
		exec.execute(pm1);  //线程执行, 对象pm1被锁，pcheck1访问pm1被阻塞， pcheck1通过计数，反映pm1同步方法执行的时间
		exec.execute(pm2);  //线程执行, 对象pm2被锁，pcheck2访问pm2被阻塞， pcheck2通过计数，反映pm2同步方法执行的时间
		exec.execute(pcheck1);
		exec.execute(pcheck2);
		
		try {
			TimeUnit.MILLISECONDS.sleep(500L);
		} catch (Exception e) {
			System.out.println("Sleep interrupted");
		}
		
		System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
		System.exit(0);
	}
	
	public static void main(String[] args) {
		PairManager pman1 = new PairManager1();
		PairManager pman2 = new PairManager2();
		testApprocaches(pman1, pman2);
		//pm1: Pair: X:18, y:18 checkCounter = 106
		//pm2: Pair: X:19, y:19 checkCounter = 47561537
		//结果分析，直接在方法上加 synchronized锁对象的时间更长，
		//而在方法内使用同步控制块锁对象的时间要短，这是为什么使用同步控制块的原因：在线程安全的情况下，使其他的线程尽可能多的访问

	}
}

class Pair{
	private int x,y;
	public Pair(int x, int y) { //Pair非线程安全的，如果别人给你一个非线程安全的类，怎么办，所以要构造一个线程安全的环境使用他
		this.x = x;
		this.y = y;
	}
	public Pair() {
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
	public class PairValuesNotEqualException extends RuntimeException{
		private static final long serialVersionUID = 2088800908104087239L;
		public PairValuesNotEqualException() {
			super("Pair values not equal:" + Pair.this);
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
abstract class PairManager{
	AtomicInteger checkCounter = new AtomicInteger(0); 
	protected Pair p = new Pair();
	private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());
	
	//通过给与的不安全的对象，重新构建一个线程安全的对象，存储时防止，p.getX(), 和 p.getY()，取到不一样的值
	public synchronized Pair getPair() {
		return new Pair(p.getX(), p.getY());
	}
	
	//store方法是将一个 Pair 对象放入到 synchronized list中，这里不需要添加
	//synchronized
	protected void store(Pair p) {
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

class PairManager1 extends PairManager{

	@Override
	public synchronized void increment() {
		p.incrementX();
		p.incrementY();
		store(getPair());
	}
}

class PairManager2 extends PairManager{
	@Override
	public void increment() {
		Pair temp;
		synchronized (this) {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		}
		store(temp);
	}
}

class PairManipulator implements Runnable{
	private PairManager pm;
	public PairManipulator(PairManager pm) {
		this.pm = pm;
	}
	@Override
	public void run() {
		while(true) {
			pm.increment();
		}
	}
	public String toString() {
		return "Pair: " + pm.getPair() + " checkCounter = " + pm.checkCounter.get();
	}
}

class PairChecker implements Runnable{
	private PairManager pm;
	public PairChecker(PairManager pm) {
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