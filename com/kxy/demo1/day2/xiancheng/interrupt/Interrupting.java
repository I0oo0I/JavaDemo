package com.kxy.demo1.day2.xiancheng.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Interrupting {
	private static ExecutorService exec = Executors.newCachedThreadPool();
	static void test(Runnable r) throws InterruptedException {
		//Future ��ʾ�첽����Ľ�������ṩ�˼������Ƿ���ɵķ������Եȴ��������ɣ�����ȡ����Ľ����
		//������ɺ�ֻ��ʹ�� get ��������ȡ��������б�Ҫ���������ǰ���������˷�����
		//ȡ������ cancel ������ִ�С����ṩ��������������ȷ��������������ɻ��Ǳ�ȡ���ˡ�
		//һ��������ɣ��Ͳ�����ȡ�����㡣���Ϊ�˿�ȡ���Զ�ʹ�� Future ���ֲ��ṩ���õĽ����
		//��������� Future<?> ��ʽ���͡������� null ��Ϊ�ײ�����Ľ���� 
		Future<?> f = exec.submit(r);
		TimeUnit.MILLISECONDS.sleep(100L);
		System.out.println("���� " + r.getClass().getName());
		//��ͼȡ���Դ������ִ�С������������ɡ�����ȡ������������ĳЩ����ԭ����޷�ȡ������˳��Խ�ʧ�ܡ�
		//������ cancel ʱ��������óɹ�������������δ��������������������С�
		//��������Ѿ��������� mayInterruptIfRunning ����ȷ���Ƿ�Ӧ������ͼֹͣ����ķ�ʽ���ж�ִ�д�������̡߳� 
		f.cancel(true);
		//f.isDone();
	}
	
	public static void main(String[] args) throws Exception {
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3L);
		System.out.println("Aborting with System.exit(0)");
		System.exit(0);
		//���������ֻ�� SleepBlocked ��ӡ�� �˳� SleepBlocked.run()��˵��ֻ��sleep�����ǿ����жϵ�
		//�� I/O �� ͬ�����������жϣ� ���������ֲ���Ҫ InterruptedException ������
		//�ر��Ƕ���I/O �п�����ס��Ķ��߳�����Ŀ���
	}
}

/**
 * sleep����
 */
class SleepBlocked implements Runnable{
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100L);
		} catch (Exception e) {
			System.out.println("Sleep�����쳣");
		}
		System.out.println("�˳� SleepBlocked.run()");
	}
}

/**
 * I/O ����
 */
class IOBlocked implements Runnable{
	private InputStream in;
	public IOBlocked(InputStream is) {
		in = is;
	}
	@Override
	public void run() {
		try {
			System.out.println("�ȴ�  read():");
			in.read();
		} catch (IOException e) {
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("I/O ����");
			}else {
				throw new RuntimeException(e);
			}
		}
		System.out.println("�˳� IOBlocked.run()");
	}
}

/**
 * ͬ��������
 */
class SynchronizedBlocked implements Runnable{
	public synchronized void f() {
		while(true) { 	//f()��Զ������
			Thread.yield();
		}
	}
	public SynchronizedBlocked() {
		new Thread() {
			@Override
			public void run() {
				f();
			}
		};
	}
	@Override
	public void run() {
		System.out.println("���� call f()");
		f();
		System.out.println("�˳� SynchronizedBlocked.run()");
	}
	
}