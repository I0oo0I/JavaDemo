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
		t.join();  //在main线程之中，调用了 t.join(), 等待 t线程执行完成，否则，main线程会抢先执行 System.out.println(a); 结果是0
		System.out.println(a);
	}

}
