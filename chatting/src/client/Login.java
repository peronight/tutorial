package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Login {
	Socket soc;
	String id, pass;
	int r;
	PrintWriter pw = null;
	BufferedReader br = null;
	
	public Login(Socket soc, String id, String pass) {
		this.soc = soc;
		this.id = id;
		this.pass = pass;
	}
	
	public void login() {
		try {
			br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			pw = new PrintWriter(soc.getOutputStream(), true);
			pw.println("login");
			br.readLine();	//서버 로그인 메서드 실행 대기
			pw.println(id);
			r = Integer.parseInt(br.readLine());
			if(r == 0) {	//id 없음
				
			}else if(r == 1) {	//id 있음
				pw.println(pass);
				int passok = Integer.parseInt(br.readLine());
				if(passok == 1) {
					r = 2;	//pass 일치
					pw.println(id);	//server loginlist add용 id 전송
					int d = Integer.parseInt(br.readLine());	//중복접속 확인값 수신
					if(d == 1) {	//중복접속일때
						r = 4;
					}
				}else if(passok == 0) {
					r = 3;	//pass 불일치
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
