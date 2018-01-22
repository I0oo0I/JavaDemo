package com.kxy.demo1.day2.xiancheng;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * java.util.concurrent ���е� ִ������Executor������Thread ���󣬼򻯲�������
 * Executor �ṩһ����Ӳ㣬������н����ִ������
 * Executor ����������첽�����ִ�У���������ʾ�ع����̵߳���������
 * 
 * @author Administrator
 *
 */
public class CacheThreadPool {

	public static void main(String[] args) {
		//����һ���̳߳أ����̳߳ظ�����Ҫ�������̣߳��������ǿ���ʱ��������ǰ�������̡߳�
		//��Щ��ͨ����Ľ�ִ���������첽����ĳ�������ܡ�������ã�����ִ�н�������ǰ�������̡߳�
		//���û�������߳̿��ã�������һ�����̲߳�������ӵ����С�δʹ��60����̱߳���ֹ���ӻ�����ɾ����
		//��ˣ�һ���������õĳز��������κ���Դ
		//��Ҫ�����̣߳����������߳�
		//�̳߳�Ϊ���޴󣬵�ִ�еڶ�������ʱ��һ�������Ѿ���ɣ��Ḵ��ִ�е�һ��������̣߳�������ÿ���½��̡߳�
		ExecutorService exec  = Executors.newCachedThreadPool();
		
		//����һ���̳߳أ����̳߳�����ʹ�ù̶��������̣߳���Щ�߳�������һ��������޽�����ϡ�
		//���κ�ʱ�򣬴����nThreads�̶߳�����������������
		//����������̶߳����ڻ״̬ʱ�ύ�˶�����������ǽ��ڶ����еȴ���ֱ���߳̿���Ϊֹ��
		//����κ��߳��ڹر�֮ǰ����ִ��ʧ�ܶ���ֹ���������Ҫִ�к��������������µ��̡߳�
		//���е��߳̽�һֱ���ڣ�ֱ����ʽ�ر�Ϊֹ��
		//ExecutorService exec = Executors.newFixedThreadPool(5);
		
		//�����߳����񣬵��ύ�˶������ʱ�����Ŷӣ��൱��  Executors.newFixedThreadPool(1)
		//ExecutorService exec = Executors.newSingleThreadExecutor();
		
		for(int i = 0; i < 5; i ++) {
			exec.execute(new LiftOff());
		}
		//��ǰ�̣߳���ִ�� shutdown�� ����֮ǰ ���ύ����������
		exec.shutdown();
		//�����ټ�һ�����ᱨ�����������޷�ִ��
		//exec.execute(new LiftOff());
	}
}
