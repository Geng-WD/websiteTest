package com.jucday01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestABC {
public static void main(String[] args) {
	PrintABC pabc=new PrintABC();
	new Thread(new Runnable() {
		@Override
		public void run() {
			for(int i=0;i<10;i++) {
			pabc.LoopA();
		}
		}
		
	}).start();
	new Thread(new Runnable() {
		@Override
		public void run() {
			for(int i=0;i<10;i++) {
			pabc.LoopB();
			}
		}
		
	}).start();
	new Thread(new Runnable() {
		@Override
		public void run() {
			for(int i=0;i<10;i++) {
			pabc.LoopC();
		}
		}
		
	}).start();
}
}
class PrintABC {
	private int i=1;
	Lock lock=new ReentrantLock();
	private Condition con1=lock.newCondition();
	private Condition con2=lock.newCondition();
	private Condition con3=lock.newCondition();
	
	public void LoopA() {
		try {
			Thread.currentThread().sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lock.lock();
		try {
			if(i!=1) {
				con1.await();
			}
			System.out.println("--A--");
			con2.signal();
			i=2;
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void LoopB() {
		lock.lock();
		try {
			if(i!=2) {
				con2.await();
			}
			System.out.println("--B--");
			con3.signal();
			i=3;
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void LoopC() {
		lock.lock();
		try {
			if(i!=3) {
				con3.await();
			}
			System.out.println("--C--");
			con1.signal();
			i=1;
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}