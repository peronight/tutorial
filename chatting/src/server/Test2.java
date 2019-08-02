package server;

class Val {
	String str = "test";
}

class Thread1 extends Thread {
	Val val = null;
	public Thread1 (Val val) {
		this.val = val;
	}
	public synchronized void run() {
		System.out.println(val.str);
		try {
			wait(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(val.str);
	}
}

public class Test2 {
	public static void main(String[] args) {
		Val val = new Val();
		Thread1 tr = new Thread1(val);
		tr.start();
		for(int i = 0; i < 1000000; i++);
		val.str = "test2";
	}
	
}
