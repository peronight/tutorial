package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
	InetAddress ia = null;
	static Socket soc = null;
	static PrintWriter pw = null;
	
	public void init() {
		try {
			ia = InetAddress.getByName("127.0.0.1");
			soc = new Socket(ia, 5656);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("접속 실패. 종료합니다.");
			System.exit(0);
		}
		UILogin login = new UILogin(soc);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.init();
		try {
			pw = new PrintWriter(soc.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				pw.println("exit");
				System.out.println("서버에 종료신호 보냄");
			}
		});
	}
}
