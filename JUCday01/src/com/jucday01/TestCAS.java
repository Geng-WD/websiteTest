package com.jucday01;

public class TestCAS {
	public static void main(String[] args) {
		
final CompareAndSwap cas=new CompareAndSwap();
for(int i=0;i<10;i++){
	new Thread(new Runnable() {

		@Override
		public void run() {
			int ev=cas.get();
			boolean b=cas.compareandSet(ev, (int)(Math.random()*101));		
			System.out.println(b);
		}	
	}).start();
}
}
}
class CompareAndSwap{
	private int value;
	public synchronized int get() {
		return value;
	}
	public synchronized int compareandswap(int expectedValue,int newValue) {
		int OldValue=value;
		if(OldValue==expectedValue) {
			this.value=newValue;
		}
		return OldValue;
	}
	public synchronized boolean compareandSet(int expectedValue,int newValue) {
		return expectedValue==compareandswap(expectedValue, newValue);
	}
}
