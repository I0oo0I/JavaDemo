package com.kxy.demo1.day2.xiancheng.data;

public class PriorityData implements Runnable{

	private int priority;
	
	private String name;
	
	public PriorityData(int priority, String name) {
		this.priority = priority;
		this.name = name;
	}
	
	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		String threadName = currentThread.getName();
		currentThread.setPriority(priority);
		for(int i = 0; i < 5; i++) {
			System.out.println("�߳����ƣ�" + threadName + ",���ȼ�"+currentThread.getPriority()+", ��ǰ���֣�"+name+"�����:" + i);
			if(i % 2 != 0) {
				Thread.yield();		//�ò�����ʾ����߳�ִ�еĲ���ˣ������ñ���߳�ʹ��cpu�ˣ���������һ�ֽ��飬����᲻�᲻֪�������Ҳ�Ǵ����ȼ��ģ���ͬ���ȼ���
									//���ܻ������У�����yield() �����ǿɿ���
			}
		}
	}

}
