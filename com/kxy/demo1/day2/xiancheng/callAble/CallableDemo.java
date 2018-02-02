package com.kxy.demo1.day2.xiancheng.callAble;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		
		List<Future<String>> results = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			//����һ���̣߳�ִ���߳����񣬷���һ�����
			results.add(exec.submit(new TaskWithResult(i)));
		}
		//main() �����̼߳���ִ�У�fs.isDone,�жϼ�����Ƿ��Ѿ�׼��������
		//û�еĻ���fs.get()ֱ�ӵ��û�����
		System.out.println("����main()�߳�,����ִ��");
		for(Future<String> fs : results) {
			try {
				if(fs.isDone()) {
					System.out.println(fs.get());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}
