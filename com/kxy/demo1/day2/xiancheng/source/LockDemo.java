package com.kxy.demo1.day2.xiancheng.source;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized �ؼ��� ��д�Ĵ����٣����ٳ���
 * ��ˣ�ͨ��ֻ�ڽ����������ʱ���Ż���ʾ��ʹ��Lock����
 * ���磺synchronized �ؼ��ֲ��ܳ����Ż�ȡ�������߳����Ż�ȡ��һ��ʱ�䣬Ȼ�������
 * ���⣬���������б��еĽڵ�Ľڴ��ݵļ������ƣ�Ҳ������ϣ������ֱ������룬�������ͷŵ�ǰ�ڵ����֮ǰ
 * ������һ�ڵ����
 * @author Administrator
 *
 */
public class LockDemo {

	public void testTryLock() {  
        Lock lock = new ReentrantLock();  
  
        new Thread() {  
            @Override  
            public void run() {  
                String tName = Thread.currentThread().getName();  
  
                if (lock.tryLock()) {  
                    System.out.println(tName + "��ȡ������");  
                } else {  
                    System.out.println(tName + "��ȡ��������");  
                    return;  
                }  
  
                try {  
  
                    for (int i = 0; i < 5; i++) {  
                        System.out.println(tName + ":" + i);  
                    }  
  
                    Thread.sleep(5000);  
                } catch (Exception e) {  
                    System.out.println(tName + "�����ˣ�����");  
                } finally {  
                    System.out.println(tName + "�ͷ�������");  
                    lock.unlock();  
                }  
  
            }  
        }.start();  
  
        new Thread() {  
            @Override  
            public void run() {  
                String tName = Thread.currentThread().getName();  
  
                if (lock.tryLock()) {  
                    System.out.println(tName + "��ȡ������");  
                } else {  
                    System.out.println(tName + "��ȡ��������");  
                    return;  
                }  
  
                try {  
                    for (int i = 0; i < 5; i++) {  
                        System.out.println(tName + ":" + i); 
                    }  
  
                } catch (Exception e) {  
                    System.out.println(tName + "�����ˣ�����");  
                } finally {  
                    System.out.println(tName + "�ͷ�������");  
                    lock.unlock();  
                }  
            }  
        }.start();  
    }  
	
	public static void main(String[] args) {
		new LockDemo().testTryLock();
	}
}
