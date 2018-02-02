package com.kxy.demo1.day2.xiancheng.waitAndNotify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Restaurant2 {
	Meal2 meal;
	Waiter wiaiter = new Waiter(this);
	Chef2 chef = new Chef2(this);
	ExecutorService exec = Executors.newCachedThreadPool();
	
	public Restaurant2(List<String> orderList) {
		meal = new Meal2(orderList);
		exec.execute(wiaiter);
		exec.execute(chef);
	}
	
	public static void main(String[] args) {
		List<String> orderList = new ArrayList<>();
		orderList.add("������˿");
		orderList.add("������");
		orderList.add("�Ǵ��Ź�");
		orderList.add("�ཷ��˿");
		orderList.add("ˮ����Ƭ");
		orderList.add("�峴С�ײ�");
		new Restaurant2(orderList);
	}
}

class Meal2{
	private volatile List<String> orderList;
	
	private volatile List<String> doneList = new ArrayList<>();
	
	public Meal2(List<String> orderList) {
		this.orderList = orderList;
	}
	
	public void cookieFood(String food) {
		doneList.add(food);
	}
	
	public List<String> getDoneList(){
		return doneList;
	}
	
	public List<String> getOrderList(){
		return orderList;
	}
}

class Waiter implements Runnable{
	
	private Restaurant2 resturant;
	
	public Waiter(Restaurant2 r) {
		this.resturant = r;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					Meal2 meal = resturant.meal;
					List<String> doneList =  meal.getDoneList();
					System.out.println("����Ա����ȡ��");
					while(null == doneList || doneList.size() == 0) {
						System.out.println("û����ɵ�ʳ�����Ա�ȴ���...");
						wait();
					}
					System.out.println("����Ա�Ѿ�ȡ�� " + doneList.toString());
					doneList.removeAll(doneList);
				}
			}
		} catch (InterruptedException e) {
			System.out.println("����Ա�˳�");
		}
	}
}

class Chef2 implements Runnable{

	private Restaurant2 restaurant;
	
	public Chef2(Restaurant2 r) {
		this.restaurant = r;
	}
	
	@Override
	public void run() {
		try {
				Meal2 meal = restaurant.meal;
				List<String> orderList =  meal.getOrderList();
				for(int i = 0; i < orderList.size(); i++) {
					System.out.println("��ʦ��ʼ���ˣ������� " + orderList.get(i) + ", ������ " + i);
					TimeUnit.MILLISECONDS.sleep(200);
					meal.cookieFood(orderList.get(i));
					System.out.println(orderList.get(i) + "�Ѿ����");
					
					synchronized (restaurant.wiaiter) { //waiterͨ��wait()�����ͷ�������chef�̵߳õ�waiter�Ķ����������
						System.out.println("��ʦ�ͷŷ���Ա");	
						List<String> doneList = meal.getDoneList();
						if(null != doneList && doneList.size() > 0) {
							restaurant.wiaiter.notifyAll();  //notifyAll(), ��chef�߳��ͷ�waiter
						}
					}
					
					if(i == (orderList.size() - 1)) {
						restaurant.exec.shutdownNow(); //ִ�����˳�, �ӽ������������ shutdownNow ֮�󣬺���� System.out.println("��ʦ�����Ѿ�ȫ�����");
					}								   //ִ���ˣ�Ҳ����˵��������shutdownNow���߳�û�������ر�
													   //������ shutdownNow�� ���ÿһ���̷߳��� interrupt()���������Ǹ�û�У����жϵģ�����������
					System.out.println("��ʦ�����Ѿ�ȫ�����"); //����catch��
				}
				TimeUnit.MILLISECONDS.sleep(200); //���ԣ����û�����sleep������System.out.println("��ʦ�˳�"); �ǲ���ִ�е�
		} catch (InterruptedException e) {
			System.out.println("��ʦ�˳�");
		}
	}
	
}
