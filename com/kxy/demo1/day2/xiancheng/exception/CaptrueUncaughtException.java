package com.kxy.demo1.day2.xiancheng.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CaptrueUncaughtException {

	public static void main(String[] args) throws InterruptedException {
		//�Զ����쳣����
		ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
		exec.execute(new ExceptionThread2());
		exec.shutdown();
		if(!exec.awaitTermination(60L, TimeUnit.SECONDS)) {
			System.out.println("�̳߳�û�йر�");
		}
		
		//�߳� run() �״���쳣�޷����ϼ����ò�׽����throw�׳����쳣��ֱ�ӵ�����̨
		ExecutorService exec1 = Executors.newCachedThreadPool();
		exec1.execute(new ExceptionThread2());
		
	}
}
