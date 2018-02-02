package com.kxy.demo1.day2.xiancheng.waitAndNotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1 {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		DemoData data = new DemoData();
		exec.execute(new Demo2(data));
		exec.execute(new Demo1(data));
		exec.shutdown();
	}
}


class Demo1 implements Runnable{
	private DemoData demoData;
	public Demo1(DemoData data) {
		this.demoData = data;
	}
	
	@Override
	public void run() {
		System.out.println("����test1");
		while(!demoData.getIsDone()) {
			demoData.test1();
		}
		System.out.println("test1ִ�����");
	}
}

class Demo2 implements Runnable{
	private DemoData demoData;
	
	public Demo2(DemoData data) {
		this.demoData = data;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("����test2");
//			while(!demoData.getIsDone()) {
//				demoData.test2();
//			}
			demoData.test2();
			System.out.println("test2ִ�����");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class DemoData{
	private volatile boolean isDone = false;
	
	public synchronized void test1() {
		isDone = true;
		System.out.println("�����ͷ�...");
		notify();
	}
	
	public synchronized void test2() throws InterruptedException {
		//�ӽ��������������notify����wait��ִ��֮��Ҫ����һ���жϣ��ж��Ƿ����ִ�У��������¹���
		//���� notifyʯ���󣬻�Ҫ�������ж�һ�Σ�����жϽ��Ϊfalse��test2�������ִ���ˣ��̼߳���������
		//���������while�ĳ�if�Ļ���notify�ͷź󣬲������ж��ˣ�����ж�ֻ���ڽ����ʱ���ж�һ�Σ�һ�����ͷţ�
		//���ͷŵ��̼߳���ִ��wait֮��Ĵ��룬��Ҳ��Ϊʲô����ж�д��whileѭ���е�ԭ��
		while(!getIsDone()) {
			System.out.println("��������...");
			wait();
		}
	}
	
	public synchronized boolean getIsDone() {
		System.out.println(Thread.currentThread().getName() + ", �����ж�");
		return isDone;
	}
}