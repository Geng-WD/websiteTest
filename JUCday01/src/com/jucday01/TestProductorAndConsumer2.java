package com.jucday01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 生产者和消费者案例
 */
public class TestProductorAndConsumer2 {
public static void main(String[] args) {
	Clerk2 clerk=new Clerk2();
	Productor2 pro=new Productor2(clerk);
	Consumer2 con=new Consumer2(clerk);
	new Thread(pro,"生产者A").start();
	new Thread(con,"消费者A").start();
	
	new Thread(pro,"生产者B").start();
	new Thread(con,"消费者B").start();
}
}

class Clerk2{
	private int product=10;
	private Lock lock=new ReentrantLock();
	private Condition condition=lock.newCondition();
	
	public void get() {
		lock.lock();
		try {
			while(product>=20) {
				System.out.println("产品已满");
				try {
					condition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				System.out.println(Thread.currentThread().getName()+":"+ ++product);
				condition.signalAll();
		}finally {
			lock.unlock();
		}
		
		
	}
	public void sale() {
		lock.lock();
		try {

			while(product<=0) {
				System.out.println("产品缺货");
				try {
					condition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

				System.out.println(Thread.currentThread().getName()+":"+ --product);
				condition.signalAll();
			
		}
		finally {
			lock.unlock();
		}
	}
}
class Productor2 implements Runnable{
	private Clerk2 clerk;

	public Productor2(Clerk2 clerk) {
		super();
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for(int i=0;i<20;i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clerk.get();
		}
		
	}
	
}
class Consumer2 implements Runnable{
	private Clerk2 clerk;
	public Consumer2(Clerk2 clerk) {
		this.clerk=clerk;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++) {
			clerk.sale();
		}
		
	}
	
}