package com.kxy.demo1.day2.xiancheng;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.kxy.demo1.day2.xiancheng.data.PriorityData;

/**
 * �߳����ȼ�
 * @author Administrator
 *
 */
public class ThreadPriority {

	public static void main(String[] args) {
		//ExecutorService exec = Executors.newCachedThreadPool();
		ExecutorService exec = Executors.newSingleThreadExecutor();
		exec.execute(new PriorityData(1, "�߳�2"));
		exec.execute(new PriorityData(5, "�߳�1"));
		exec.shutdown();
		//�����еĽ�����������������ȼ��ߵģ���һ�����������У��߳�Ӧ��������ģ�ֻ�����ȼ��ߵ������еĸ���Ҫ��һЩ
	}
}
