package com.kxy.demo1.day2.xiancheng.interrupt;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class NIOInterruption{
	public static void main(String[] args) throws IOException, InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
		SocketChannel sc1 = SocketChannel.open(isa);
		SocketChannel sc2 = SocketChannel.open(isa);
		
		Future<?> f = exec.submit(new NIOBlocked(sc1));
		exec.execute(new NIOBlocked(sc2));
		exec.shutdown();
		TimeUnit.SECONDS.sleep(1);
		f.cancel(true);
		TimeUnit.SECONDS.sleep(1);
		sc2.close();	//通过关闭底层资源，来打断阻塞的synchronized同步线程，没有这个，程序一直阻塞
	}
}

class NIOBlocked implements Runnable{
	private final SocketChannel sc;
	public NIOBlocked(SocketChannel sc) {
		this.sc = sc;
	}
	@Override
	public void run() {
		try {
			System.out.println("等待读取..." + this);
			sc.read(ByteBuffer.allocate(1));
		}catch (ClosedByInterruptException e) {
			System.out.println("ClosedByInterruptException");
		}catch (AsynchronousCloseException e) {
			System.out.println("AsynchronousCloseException");
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("退出NIOBlocked.run() " + this);
	}
	
}


