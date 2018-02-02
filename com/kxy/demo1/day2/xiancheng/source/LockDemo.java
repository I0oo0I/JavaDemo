package com.kxy.demo1.day2.xiancheng.source;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 关键字 ，写的代码少，更少出错。
 * 因此，通常只在解决特殊问题时，才会显示的使用Lock对象。
 * 例如：synchronized 关键字不能尝试着获取锁，或者尝试着获取锁一段时间，然后放弃它
 * 另外，遍历链接列表中的节点的节传递的加锁机制（也称锁耦合），这种遍历代码，必须在释放当前节点的锁之前
 * 捕获下一节点的锁
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
                    System.out.println(tName + "获取到锁！");  
                } else {  
                    System.out.println(tName + "获取不到锁！");  
                    return;  
                }  
  
                try {  
  
                    for (int i = 0; i < 5; i++) {  
                        System.out.println(tName + ":" + i);  
                    }  
  
                    Thread.sleep(5000);  
                } catch (Exception e) {  
                    System.out.println(tName + "出错了！！！");  
                } finally {  
                    System.out.println(tName + "释放锁！！");  
                    lock.unlock();  
                }  
  
            }  
        }.start();  
  
        new Thread() {  
            @Override  
            public void run() {  
                String tName = Thread.currentThread().getName();  
  
                if (lock.tryLock()) {  
                    System.out.println(tName + "获取到锁！");  
                } else {  
                    System.out.println(tName + "获取不到锁！");  
                    return;  
                }  
  
                try {  
                    for (int i = 0; i < 5; i++) {  
                        System.out.println(tName + ":" + i); 
                    }  
  
                } catch (Exception e) {  
                    System.out.println(tName + "出错了！！！");  
                } finally {  
                    System.out.println(tName + "释放锁！！");  
                    lock.unlock();  
                }  
            }  
        }.start();  
    }  
	
	public static void main(String[] args) {
		new LockDemo().testTryLock();
	}
}
