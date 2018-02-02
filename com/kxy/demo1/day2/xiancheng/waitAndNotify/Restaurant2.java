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
		orderList.add("鱼香肉丝");
		orderList.add("红烧肉");
		orderList.add("糖醋排骨");
		orderList.add("青椒肉丝");
		orderList.add("水煮鱼片");
		orderList.add("清炒小白菜");
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
					System.out.println("服务员进入取餐");
					while(null == doneList || doneList.size() == 0) {
						System.out.println("没有完成的食物，服务员等待中...");
						wait();
					}
					System.out.println("服务员已经取餐 " + doneList.toString());
					doneList.removeAll(doneList);
				}
			}
		} catch (InterruptedException e) {
			System.out.println("服务员退出");
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
					System.out.println("厨师开始做菜，菜名是 " + orderList.get(i) + ", 订单号 " + i);
					TimeUnit.MILLISECONDS.sleep(200);
					meal.cookieFood(orderList.get(i));
					System.out.println(orderList.get(i) + "已经完成");
					
					synchronized (restaurant.wiaiter) { //waiter通过wait()方法释放锁后，让chef线程得到waiter的对象监视器，
						System.out.println("厨师释放服务员");	
						List<String> doneList = meal.getDoneList();
						if(null != doneList && doneList.size() > 0) {
							restaurant.wiaiter.notifyAll();  //notifyAll(), 由chef线程释放waiter
						}
					}
					
					if(i == (orderList.size() - 1)) {
						restaurant.exec.shutdownNow(); //执行完退出, 从结果看，调用了 shutdownNow 之后，后面的 System.out.println("厨师订单已经全部完成");
					}								   //执行了，也就是说，调用了shutdownNow，线程没有立即关闭
													   //调用了 shutdownNow， 会给每一个线程发送 interrupt()方法，若那个没有（可中断的）阻塞，不会
					System.out.println("厨师订单已经全部完成"); //进入catch块
				}
				TimeUnit.MILLISECONDS.sleep(200); //所以，如果没有这个sleep操作，System.out.println("厨师退出"); 是不会执行的
		} catch (InterruptedException e) {
			System.out.println("厨师退出");
		}
	}
	
}
