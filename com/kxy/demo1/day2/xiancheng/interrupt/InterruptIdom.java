package com.kxy.demo1.day2.xiancheng.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptIdom {
	public static void main(String[] args) throws NumberFormatException, InterruptedException {
		if(args.length != 1) {
			System.out.println("usage: java InterrruptIdiom delay-in-mS");
			//��ֹ��ǰ���е�Java��������ò�������״̬����;���չ���������״̬���ʾ�쳣��ֹ��
//			�÷�����������ʱ����exit���������ַ��������������ء�
//			����System.exit(n)��Ч�ڵ���:
//			Runtime.getRuntime().exit(n)
			System.exit(1);
		}
		Thread t = new Thread(new Block3());
		t.start();
		TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
		t.interrupt();
	}
}

class NeedsCleanup{
	private final int id;
	public NeedsCleanup(int ident) {
		id = ident;
		System.out.println("��Ҫ���� " + id);
	}
	public void cleanup() {
		System.out.println("���� " + id);
	}
}

class Block3 implements Runnable{
	private volatile double d = 0.0;
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				NeedsCleanup n1 = new NeedsCleanup(1);
				try {
					System.out.println("Sleeping");
					TimeUnit.SECONDS.sleep(1);
					
					NeedsCleanup n2 = new NeedsCleanup(2);
					try {
						System.out.println("Calculating");
						for(int i = 1; i < 2500000; i++) {
							d = d + (Math.PI + Math.E) / d;
						}
						System.out.println("Finished time-consuming operation");
					} finally {
						n2.cleanup();
					}
				} finally {
					n1.cleanup();
				}
			}
			System.out.println("Exiting via while() test");
		} catch (InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		}
	}
	
}
