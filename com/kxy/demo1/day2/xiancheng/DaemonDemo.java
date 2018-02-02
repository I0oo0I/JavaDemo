package com.kxy.demo1.day2.xiancheng;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.kxy.demo1.day2.xiancheng.data.DaemonThreadFactory;
import com.kxy.demo1.day2.xiancheng.data.SimpleDaemonData;

/**
 * �ػ��̣߳��ֽк�̨�߳�
 * @author Administrator
 *
 */
public class DaemonDemo {

	//��̨�̣߳����ڳ����������ں�̨���е�ͨ�÷�����̣߳����Һ�̨�̲߳����ǳ����в��ɻ�ȱ�Ĳ��֡�
	//main��һ���Ǻ�̨�̣߳������еķǺ�̨�̶߳�������ʱ�򣬳���ͽ����ˣ�ͬʱ��ɱ�����������еĺ�̨�߳�
	//���������һ�γ����У�daemon������Ϊ�˺�̨�̣߳�main�������н����ˣ���Щ�̶߳��ᱻɱ��
	//mian �߳� ����������  TimeUnit.MILLISECONDS.sleep(175); daemon����������
	//main�߳����ߵ�ʱ�䣬Ҳ���� ��̨�߳����е�ʱ��
	public static void main(String[] args) throws InterruptedException {
		//DaemonThreadFactory �����̵߳ģ���̨�����ȼ������Ƶȣ�
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for(int i = 0; i < 10; i++) {
			//DaemonThreadFactory ����һ�²���
//			Thread daemon = new Thread(new SimpleDaemonData());
//			daemon.setDaemon(true);
//			daemon.start();
			
			exec.execute(new SimpleDaemonData());
		}
		exec.shutdown();
		
		System.out.println("��ʼ�����ػ�����̨���߳�");
		TimeUnit.MILLISECONDS.sleep(175);
	}
}
