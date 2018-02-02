package com.kxy.demo1.day2.xiancheng.source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EvenChecker implements Runnable{

	private IntGenerator generator;
	private final int id;
	private static List<Integer> list = new ArrayList<>();
	
	public EvenChecker(IntGenerator g, int ident) {
		generator = g;
		id = ident;
	}
	
	@Override
	public void run() {
//		while(!generator.isCanceled()) {
//			int value = generator.next(); //现在才是生成的结果，id只是代表线程
//			System.out.println("线程"+id+"进入, isCanceled = " + generator.isCanceled()
//			+ ", 此时的 value = " + value);
//			if(value % 2 == 0) {
//				System.out.println("线程"+id+"," + value + "是偶数");
//				generator.cancel();  //取消循环
//			}
//		}
//		System.out.println("线程"+id+"结束, isCanceled = " + generator.isCanceled());
		
		int a = generator.next();
		while(a % 2 != 0) {
			a = generator.next();
		}
		System.out.println("线程"+id+"结束, value = " + a);
		list.add(a);
	}
	
	public static void test(IntGenerator gp, int count) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 1; i < count; i++) { //循环没什么用，开count个线程
			exec.execute(new EvenChecker(gp, i));  
		}
		exec.shutdown();
		
		try {
			if(!exec.awaitTermination(60L, TimeUnit.SECONDS)) {
				exec.shutdownNow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		for(Integer l : list) {
			if(map.containsKey(l)) {
				map.put(l, map.get(l).intValue() + 1);
			}else {
				map.put(l, 1);
			}
		}
		
		Set<Integer> set = map.keySet();
		Iterator<Integer> it = set.iterator();
		StringBuffer buffer = new StringBuffer();
		while(it.hasNext()) {
			Integer key = it.next();
			if(map.get(key) > 1) {
				buffer.append(key + ":" + map.get(key) + "次\n");
			}
		}
		if(null == buffer || buffer.length() == 0) {
			System.out.println("没有重复数字");
		}else {
			System.out.println(buffer.toString());
		}
		
		
	}
	
	public static void test(IntGenerator gp){
		test(gp, 20);
	}

}
