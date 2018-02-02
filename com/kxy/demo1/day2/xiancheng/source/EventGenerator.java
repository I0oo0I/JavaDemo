package com.kxy.demo1.day2.xiancheng.source;

public class  EventGenerator extends IntGenerator{

	private  int currentEvenValue = 0;
	
	/**
	 * synchronized  ��û������ؼ��֣�����ǲ�һ����
	 */
	@Override
	public synchronized int next() {
		++currentEvenValue;  //��ԭ�Ӳ������ù��̰�������ԭ�Ӳ����� currentEvenValue = currentEvenValue + 1
							// ��ȡ  currentEvenValue  currentEvenValue+1   ��ֵ��currentEvenValue
							// ���������̲߳����жϣ�����������ԭ�Ӳ���֮��ᱻ�߳��жϹ�����Ҳ��û�������������ԭ��
		Thread.yield(); 	// �ӿ��̵߳��л�
							// https://www.cnblogs.com/huanmin/p/6156192.html,
							// http://blog.csdn.net/gds2014/article/details/50317145
		return currentEvenValue;
	}
	
	public static void main(String[] args) {
		EvenChecker.test(new EventGenerator());
	}

}
