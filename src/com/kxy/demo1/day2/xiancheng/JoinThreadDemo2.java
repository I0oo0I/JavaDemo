package com.kxy.demo1.day2.xiancheng;

public class JoinThreadDemo2 implements Runnable{

	public static int a = 0;
	
	@Override
	public void run() {
		while(a < 5) {
			a++;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new JoinThreadDemo2());
		t.start();
		t.join();  //��main�߳�֮�У������� t.join(), �ȴ� t�߳�ִ����ɣ�����main�̻߳�����ִ�� System.out.println(a); �����0
		System.out.println(a);
	}

}
