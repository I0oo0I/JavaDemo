package com.kxy.demo1.day2.xiancheng.source;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockData {

	private int currentEventValue = 0;
	
	private Lock lock = new ReentrantLock(); 
	
	public int next() {
		lock.lock(); 
		try {
			++currentEventValue;
			Thread.yield(); 
			return currentEventValue;
		} finally {
			lock.unlock();
		}
	}
}
