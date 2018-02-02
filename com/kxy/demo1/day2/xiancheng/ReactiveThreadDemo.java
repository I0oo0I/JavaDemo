package com.kxy.demo1.day2.xiancheng;

import java.io.IOException;

public class ReactiveThreadDemo extends Thread{

	//���� volatile �ؼ���, ԭ��ͬ���� �������߳����޸ģ�������߳����Ͽɼ���������ͬ���̳߳��ֵ�ֵ��һ��
	// https://www.cnblogs.com/zhengbin/p/5654805.html 
	private static volatile double d = 1;
	
	public ReactiveThreadDemo() {
		setDaemon(true);
		start();
	}
	
	@Override
	public void run() {
		//��̨һֱ����
		while(true) {
			d = d + (Math.PI + Math.E) / d;
			System.out.println("thread d:" + d);
		}
	}
	
	public static void main(String[] args) throws IOException {
		//new UnresponsiveUI();
		
		//��̨�߳�һֱ������d = d + (Math.PI + Math.E) / d�� ֱ��main�߳̽���
		new ReactiveThreadDemo(); 
		
		System.in.read();
		System.out.println("main d :" + d);
	}
}

class UnresponsiveUI{
	private volatile double d = 1;
	public UnresponsiveUI() throws IOException {
		while (d > 0) {
			d = d + (Math.PI + Math.E) /d ;
			System.out.println("d:" + d);
			System.in.read();
		}
	}
}
