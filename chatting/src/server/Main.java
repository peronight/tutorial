package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Main implements Runnable{
	Server server = null;
	Socket soc = null;
	LoginList logList = null;
	RoomList roomList = null;
	PrintWriter pw = null;
	ServerSocket server2 = null;
	
	public void init() {
		server = new Server();
		server.init();
		logList = new LoginList();
		roomList = new RoomList();
		try {
			server2 = new ServerSocket(5454);	//list 전송용 socket
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("접속 대기중");
		while(true) {
			try {
				soc = server.server.accept();
				System.out.println("클라이언트로부터 접속" + soc.getPort());
				Thread tr = new Thread(this);
				tr.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		Socket soc2 = soc;
//		Socket soc2 = null;
//		try {
//			soc2 = new Socket(soc.getInetAddress(), soc.getPort(), soc.getLocalAddress(), soc.getLocalPort());
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		BufferedReader in = null;
		String r = null;
		String id = null;
		try {
			in = new BufferedReader(new InputStreamReader(soc2.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
			try {
				r = in.readLine();
				System.out.println(r);
				if(r.equals("exit")) {
					break;
				}else if(r.equals("dupcheck") || r.equals("join")) {
					MemberRegist mr = new MemberRegist(soc2, r);
					mr.init();
				}else if(r.equals("login")) {
					Login login = new Login(soc2);
					login.login();
					if(login.r == 3) {	//로그인 성공시
						id = in.readLine();
						pw = new PrintWriter(soc2.getOutputStream(), true);
						if(logList.list.contains(id)) {
							pw.println("1");	//중복 로그인
						}else {
							pw.println("0");
							System.out.println("중복 로그인 아님");
							logList.add(id);
							break;
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				logList.del(id);
				break;
			}
		}
		Socket soc3 = null;
		try {
			soc3 = server2.accept();	//list 전송용 socket
			System.out.println("list 전송용 socket 생성됨");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ListService lists = new ListService(soc3);
		while(true) {
			try {
				r = in.readLine();
				if(r.equals("exit")) {
					logList.del(id);
					break;
				}else if(r.equals("refresh")) {
					System.out.println("refresh 요청받음");
					lists.sendList(logList.list, roomList.list);
				}
			} catch (IOException e) {
				e.printStackTrace();
				logList.del(id);
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.init();
	}

	
}
