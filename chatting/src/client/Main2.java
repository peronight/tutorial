package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

//class Refresh extends Thread {
//	Socket soc = null;
//	Socket soc2 = null;
//	ArrayList<String> logList = null;
//	ArrayList<String> roomList = null;
//	BufferedReader br = null;
//	ObjectInputStream ois = null;
//	String r = null;
//	
//	public Refresh(Socket soc, Socket soc2) {
//		this.soc = soc;
//		this.soc2 = soc2;
//		this.logList = new ArrayList<String>();
//		this.roomList = new ArrayList<String>();
//	}
//	
//	public void run() {
//		try {
//			br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
//			ois = new ObjectInputStream(soc2.getInputStream());
//			while(true) {
//				System.out.println("refresh stand by");
//				System.out.println(soc.getInputStream());
//				r = br.readLine();
//				System.out.println(r);
//				System.out.println("refresh receive");
//				if(r.equals("userrefresh")) {
//					System.out.println("loglist receive ready");
//					logList = (ArrayList<String>)ois.readObject();
//					System.out.println("loglist receive");
//				}else if(r.equals("roomrefresh")) {
//					roomList = (ArrayList<String>)ois.readObject();
//					System.out.println("roomlist receive");
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//}

public class Main2 implements Runnable{
	Socket soc = null;
	Socket soc2 = null;
	PrintWriter pw = null;
	String id = null;
	ArrayList<String> logList = null;
	ArrayList<String> roomList = null;
//	Refresh refresh = null;
	ObjectInputStream ois = null;
	
	public synchronized void run() {
		while(true) {
			pw.println("refresh");
			try {
				System.out.println((ArrayList<String>)ois.readObject());
//				logList = (ArrayList<String>)ois.readObject();
				roomList = (ArrayList<String>)ois.readObject();
				System.out.println(logList);
				wait(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Main2(Socket soc, String id) {
		this.soc = soc;
		this.id = id;
		try {
			pw = new PrintWriter(this.soc.getOutputStream(), true);
			InetAddress ia = InetAddress.getByName("127.0.0.1");
			this.soc2 = new Socket(ia, 5454);	//list 수신용 socket
			System.out.println("list용 socket 생성됨");
			ois = new ObjectInputStream(this.soc2.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logList = new ArrayList<String>();
		roomList = new ArrayList<String>();
		Thread tr = new Thread(this);
		tr.start();
	}
}
