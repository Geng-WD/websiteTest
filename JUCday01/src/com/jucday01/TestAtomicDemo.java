package com.jucday01;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

//volatile i++的原子性问题
/*
 * 原子变量（volatile保证内存可见性，CAS算法保证数据的原子性，Compare and Swap）
 */
public class TestAtomicDemo {
	
	/*
	@Test
	public void test() {
		int i=10;
		i=i++;
		System.out.println(i);
		// int temp=i;
		 //i=i+1;
		  //i=temp;
	}
	*/ 
	public static void main(String[] args) {
		AtomicDemo ad=new AtomicDemo();
		for(int i=0;i<10;i++) {
			new Thread(ad).start();
		}

	}
 
}
class AtomicDemo implements Runnable{
//	private volatile int serialNumber=0;
	private AtomicInteger serialNumber=new AtomicInteger();

	public int getSerialNumber() {
		return serialNumber.getAndIncrement();
	}

	public void setSerialNumber(AtomicInteger serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {;
		}
		System.out.println(Thread.currentThread().getName()+":"+getSerialNumber());
		
	}
	
}
