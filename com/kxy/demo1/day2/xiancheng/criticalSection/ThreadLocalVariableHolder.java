package com.kxy.demo1.day2.xiancheng.criticalSection;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalVariableHolder {
	private static final AtomicInteger threadId = new AtomicInteger(0);
	/**
	 * ������ṩ���ֲ߳̾���������Щ������������Ӧ�ı�����ͬ��
	 * ��Ϊÿ������һ���̵߳��߳�(ͨ������get��set����)�����Լ��ġ�������ʼ���ı���������
	 * ThreadLocalʵ��ͨ�������е�˽�о�̬�ֶΣ�ϣ����״̬���̹߳���(���磬�û�ID������ID)��
	 */
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		private Random rand = new Random(47); //�����������Ժ󣬶�ͬһ���̣߳�ȡ��ֵ����ͬ�ģ��Բ�ͬ���߳�ȡ��ֵ�ǲ�ͬ��
		
		/**
		 * ���ش��ֲ߳̾������ĵ�ǰ�̵߳ġ���ʼֵ�����̵߳�һ��ʹ�� get() �������ʱ���ʱ�����ô˷�����
		 * ������߳�֮ǰ������ set(T) �������򲻻�Ը��߳��ٵ��� initialValue ������
		 * ͨ�����˷�����ÿ���߳�������һ�Σ�������ڵ��� get() ���ֵ����� remove()��������ٴε��ô˷����� 
		 * ��ʵ�ַ��� null���������Աϣ���ֲ߳̾��������� null �����ֵ�������Ϊ ThreadLocal �������࣬����д�˷�����
		 * ͨ����ʹ�������ڲ�����ɴ˲����� 
		 */
		protected synchronized Integer initialValue() { 
			//return rand.nextInt(10);  //Random������value��һ���ǲ���ȵģ���Ȼ�����̵߳ı������Ƕ����ģ����ǲ�ͬ�߳�֮��Ҳ���ܻ������ֵͬ
			return threadId.getAndIncrement();
		}
		
		
	};
	
	public static void increment() {
		value.set(value.get() + 1);
	}
	
	public static int get() {
		return value.get();
	}
	
	public static void main(String[] args) throws InterruptedException {
//		ExecutorService exec = Executors.newCachedThreadPool();
//		for(int i = 0; i < 5; i++) {
//			exec.execute(new Accessor(i));
//		}
//		TimeUnit.MILLISECONDS.sleep(1L);
//		exec.shutdownNow();
		System.out.println(Thread.currentThread().getName() + ", value : " + value.get());
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++) {
			exec.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ", value : " + value.get());
				}
			});
		}
	}
}

class Accessor implements Runnable{
	private final int id;
	public Accessor(int idn) {
		this.id = idn;
	}
	
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}
	
	@Override
	public String toString() {
		return "#" + id + ": " + ThreadLocalVariableHolder.get();
	} 
}