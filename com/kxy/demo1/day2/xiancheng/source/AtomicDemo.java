package com.kxy.demo1.day2.xiancheng.source;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AtomicDemo implements Runnable{

	private volatile int i = 0;
	
	public synchronized int getValue() {
		return i;
	}
	
	/**
	 * synchronized, ��֤���ǣ�ֻ�ܱ�ͬһ�̷߳��ʣ���������ʹ����߳�ִ�е�ʱ�䵽�ˣ�
	 * �̱߳�������һ�λ��ǽ���ִ�У� ������˵���������һ��ִ����
	 */
	private synchronized void evenIncrement() {
		try {
			System.out.println("��ǰ�߳�" + Thread.currentThread().getName() + ", i = " + i);
			i++;
			TimeUnit.SECONDS.sleep(1L);
			i++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//i = i + 2;
	}
	
	@Override
	public void run() {
		while(true) {
			evenIncrement();
			//System.out.println("true");
		}
	}
	
	/**
		Ҫ�����������⣬1.���Ķ�����˭��2.˭����������
		���跽��A��B����ͬһ����Test�е�����������Test t=new Test(); t.methodB();
		���ʱ��methodB����������ʱ����Ϊ����synchronized ����Ҫ�Ȼ��һ������������Ķ���Ӧ����t��
		Ҳ���ǵ�ǰ�����Test���ʵ������������Ķ������̣߳�Ҳ����˵��ǰ�߳��õ���t��������������˵��B�������������
		���ʱ��B�����ڵ���methodA����ΪAҲ����synchronized��Ҳ��Ҫ���һ������
		��ΪA��B����Test���еķ��������Ե�ǰ�߳�Ҫ��õ����Ķ���Ҳ��t��
		���ڵ�ǰ�߳���ִ��B����ʱ�Ѿ�������t��������������ʱ�����methodA��û���κ�Ӱ��ģ�
		�൱�ڷ���A��û�м�synchronized��
		��һ���������������������Test��Test t1=new Test(); Test t2=new Test(); t1.methodB();
			��ʱ��ǰ�̳߳�����t1�������t2.methodB();
			��ʱ��ǰ�߳�Ҳ������t2���������ǰ�̳߳����������������Ķ���ֱ���������ͬ��Test���ʵ��t1��t2��
			����û��Ӱ�졣
		��һ������������ڶ��̻߳����£������̶߳����Է���Test t=new Test(); 
			��ʱ����thread1�����t.methodB();
			ͬʱthread2�����t.methodB()��ʱ����thread1������t�������,
			��ôthread2��Ҫ�ȴ�thread1�ͷ�t��������ſ���ִ��B������
			�����������thread1���t����--thread1ִ��methodB--thread1ִ��methodA--�ͷ�t����---
					 thread2���t����--thread2ִ��methodB--thread2ִ��methodA--�ͷ�t������
			synchronized���кܶ���ʹ�÷�������ֻ�������������̻߳���ĸ�����������ͺ����������ˡ�
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicDemo demo = new AtomicDemo();
		exec.execute(demo);
		
		//������������̣߳�main�̺߳� AtomicDemo �߳�
		//main�߳�ѭ���ж�����������ӡ��������������
		//AtomicDemo �߳�ѭ������ż��
		//��Ҫ�� i++, i++���м�ֵ�������ˣ�
		//��getValue()ʹ��synchronized, Ϊʲô��������������������ˣ�������
		// AtomicDemo demo = new AtomicDemo(); demo�����߳��ǹ����ͬһ��
		// exec���ɵ��߳�ִ�� evenIncrementʱ����ס�˶��� demo�� ���е�ͬ����������
		// main�߳� ���� demo.getValue(); ��������ֻ��exec�̷߳����꣬main�̲߳��ܻ�ȡ
		// demo���������ִ��  demo.getValue()��������˲�������߳�ͬ������
		while(true) {
			int val = demo.getValue();
			System.out.println("��ǰ�߳�" + Thread.currentThread().getName() + ", val = " + val);
			if(val > 100 && val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}

}
