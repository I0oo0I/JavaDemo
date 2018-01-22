package com.kxy.demo1.day2.xiancheng;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.kxy.demo1.day2.xiancheng.data.DaemonThreadFactory;
import com.kxy.demo1.day2.xiancheng.data.SimpleDaemonData;

/**
 * 守护线程，又叫后台线程
 * @author Administrator
 *
 */
public class DaemonDemo {

	//后台线程，是在程序运行在在后台运行的通用服务的线程，并且后台线程并不是程序中不可或缺的部分。
	//main是一个非后台线程，当所有的非后台线程都结束的时候，程序就结束了，同时会杀死进程中所有的后台线程
	//因此在下面一段程序中，daemon被设置为了后台线程，main方法运行结束了，这些线程都会被杀死
	//mian 线程 不设置休眠  TimeUnit.MILLISECONDS.sleep(175); daemon都不会运行
	//main线程休眠的时间，也就是 后台线程运行的时间
	public static void main(String[] args) throws InterruptedException {
		//DaemonThreadFactory 定制线程的（后台，优先级，名称等）
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for(int i = 0; i < 10; i++) {
			//DaemonThreadFactory 代替一下操作
//			Thread daemon = new Thread(new SimpleDaemonData());
//			daemon.setDaemon(true);
//			daemon.start();
			
			exec.execute(new SimpleDaemonData());
		}
		exec.shutdown();
		
		System.out.println("开始进行守护（后台）线程");
		TimeUnit.MILLISECONDS.sleep(175);
	}
}
