package com.kxy.demo1.day2.xiancheng.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockDemo {
	public static void main(String[] args) throws InterruptedException {
		//��main�߳��� ִ���� new Blocked2() ������ BlockedMutex����main�߳���ס
		//t�̵߳�run���������ʱ����� BlockedMutex����� f()�������޷���ȡ����������blocked.f();һֱ����
		Thread t = new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("�߳�t�ж�");
		t.interrupt(); // interrupt���Դ�ϱ�����������ĵ���
	}
}

class BlockedMutex{
	private Lock lock = new ReentrantLock(); //ReentrantLock��������������Ա����
	public BlockedMutex(){
		System.out.println("��ǰ�̣߳�" + Thread.currentThread().getName());
		lock.lock();
	}
	
	public void f() {
		try {
//			�����ǰ�߳�δ���жϣ����ȡ���� 
//			��������ã����ȡ�������������ء� 
//			����������ã������̵߳���Ŀ�ģ������õ�ǰ�̣߳������ڷ��������������֮һ��ǰ�����߳̽�һֱ��������״̬�� 
//				���ɵ�ǰ�̻߳�ã�
//				��������ĳ���߳��жϵ�ǰ�̣߳�����֧�ֶ�����ȡ���жϡ� 
//			�����ǰ�̣߳� 
//				�ڽ���˷���ʱ�Ѿ������˸��̵߳��ж�״̬��
//				�����ڻ�ȡ��ʱ���жϣ�����֧�ֶ�����ȡ���жϣ� 
//			���׳� InterruptedException���������ǰ�̵߳����ж�״̬��
			lock.lockInterruptibly();
			System.out.println("f()��ȡ����");
		} catch (InterruptedException e) {
			System.out.println("�ж�f()�����Ļ�ȡ");
		}
	}
}

class Blocked2 implements Runnable{
	BlockedMutex blocked = new BlockedMutex();
	@Override
	public void run() {
		System.out.println("�ȴ�f()����");
		blocked.f();
		System.out.println("�жϺ���");
	}
}
