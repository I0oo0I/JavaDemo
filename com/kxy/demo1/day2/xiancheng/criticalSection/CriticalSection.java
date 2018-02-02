package com.kxy.demo1.day2.xiancheng.criticalSection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * �������ͷ�����ͬ�����ƿ�ıȽ�
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
		
		exec.execute(pm1);  //�߳�ִ��, ����pm1������pcheck1����pm1�������� pcheck1ͨ����������ӳpm1ͬ������ִ�е�ʱ��
		exec.execute(pm2);  //�߳�ִ��, ����pm2������pcheck2����pm2�������� pcheck2ͨ����������ӳpm2ͬ������ִ�е�ʱ��
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
		//���������ֱ���ڷ����ϼ� synchronized�������ʱ�������
		//���ڷ�����ʹ��ͬ�����ƿ��������ʱ��Ҫ�̣�����Ϊʲôʹ��ͬ�����ƿ��ԭ�����̰߳�ȫ������£�ʹ�������߳̾����ܶ�ķ���

	}
}

class Pair{
	private int x,y;
	public Pair(int x, int y) { //Pair���̰߳�ȫ�ģ�������˸���һ�����̰߳�ȫ���࣬��ô�죬����Ҫ����һ���̰߳�ȫ�Ļ���ʹ����
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
 * �����̰߳�ȫ�Ļ���ʹ���� Pair
 * @author Administrator
 *
 */
abstract class PairManager{
	AtomicInteger checkCounter = new AtomicInteger(0); 
	protected Pair p = new Pair();
	private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());
	
	//ͨ������Ĳ���ȫ�Ķ������¹���һ���̰߳�ȫ�Ķ��󣬴洢ʱ��ֹ��p.getX(), �� p.getY()��ȡ����һ����ֵ
	public synchronized Pair getPair() {
		return new Pair(p.getX(), p.getY());
	}
	
	//store�����ǽ�һ�� Pair ������뵽 synchronized list�У����ﲻ��Ҫ���
	//synchronized
	protected void store(Pair p) {
		storage.add(p);
		try {
			TimeUnit.MILLISECONDS.sleep(50L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//�� Pair �Ĳ���ȫ�Ĳ���������һ���̰߳�ȫ�ķ�����
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