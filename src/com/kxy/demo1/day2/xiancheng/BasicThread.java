package com.kxy.demo1.day2.xiancheng;

public class BasicThread {

	public static void main(String[] args) {
		//������󶨵��߳�
		//start(),ִ���߳�ִ�еĳ�ʼ������
		//ִ��Runable��run�����������߳�����������
		Thread t = new Thread(new LiftOff());
		t.start();
		//��ִ�еĽ����start()֮�󣬴�ӡ����  Watting for LiftOff
		//֮����ֵ���  run() ���������ݣ�run�������������߳�ִ�еģ�
		//main�������̣߳���Ȼִ����  System.out.println("Watting for LiftOff"); ����
		//�������������������main�̣߳����������̶߳�������������һ���߳�
		System.out.println("Watting for LiftOff"); 
		
		for(int i = 0; i < 5; i++) {
			//ѭ����������߳�
			System.out.println();
			new Thread(new LiftOff()).start();
			//ִ�еĽ��������ģ���������һ��˵���̲߳���һ��ֱ��ִ�����ˣ����ִ��һ�㣬
			//������һ���߳�ִ��һ�㡣�����̵߳��������Ƶģ����������ж�����������߳�
			//������������Щ������֮��ĬĬ�ķַ��߳�
		}
	}
}
