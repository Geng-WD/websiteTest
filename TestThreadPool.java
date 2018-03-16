package com.jucday01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * 线程池 ，提供一个线程队列，队列中保存着所有等待状态的线程，避免了创建和销毁的额外开销，提高响应的速度
 * 
 * 体系结构
 * java.util.concurrent.Executor：负责线程池的使用和调度的根借口
 * ---**ExecutorService 子接口：线程池的主要接口
 * ---**ThreadPoolExecutor :线程池的实现类
 * ---** ScheduledExecutorService：子接口：负责线程的调度
 * ----** ScheduledThreadPoolExecutor：继承“ThreadPoolExecutor，实现ScheduledExecutorService
 * 
 * 工具类Executors
 * ExecutorService newFixedThreadPool() :创建固定大小的线程池
 * ExecutorService newCachedThreadPool() :缓存线程池
 * ExecutorService newSingleThreadPool() :创建单个的线程池
 * 
 * ExecutorService newScheduledThreadPool() :创建固定大小的线程池，可以延迟或定时执行任务
 */
public class TestThreadPool {
  public static void main(String[] args) {
		//1创建线程池
		ExecutorService pool=Executors.newFixedThreadPool(5);
//		
//		ThreadPoolDemo tpd=new ThreadPoolDemo();
//	
//		//2、为线程池中线程的分配任务
//		for(int i=0;i<10;i++) {
//			pool.submit(tpd);
//		}
//		
//		//3关闭线程池
//		pool.shutdown();
	  
		Future<Integer> future=pool.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				int sum=0;
				for(int i=0;i<=100;i++) {
					sum+=i;
				}
				return sum;
			}
			
		});
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
class ThreadPoolDemo implements Runnable{
	private int i=0;
	


	@Override
	public void run() {
		while(i<=100) {
			System.out.println(Thread.currentThread().getName()+":"+i++);
		}
	}
	
}
