package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MemberRegist {
	String id;
	String pass;
	String mail;
	int joined;
	InetAddress ia = null;
	Socket soc = null;
	PrintWriter pw = null;
	BufferedReader br = null;
	
	public MemberRegist(Socket soc) {
		this.soc = soc;
	}
	
	public int dupCheck(String id) {
		System.out.println("중복체크 메서드 실행");
		int r = 0;
		try {
			pw = new PrintWriter(soc.getOutputStream(), true);
			pw.println("dupcheck");	//중복체크 요청보냄
			br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			System.out.println("id송신 대기");
			br.readLine();			//id송신 대기
			pw.println(id);
			System.out.println("id 송신 완료");
			
			r = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public int memberJoin(String id, String pass, String mail) {
		this.id = id;
		this.pass = pass;
		this.mail = mail;
		
		try {
			System.out.println("서버에 접속");
			br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			pw = new PrintWriter(soc.getOutputStream(), true);
			pw.println("join");
			br.readLine();			//서버의 가입 준비 신호 대기
			System.out.println("서버 응답함");
			pw.println(id);
			System.out.println("id send");
			br.readLine();
			System.out.println("pass send ready");
			pw.println(pass);
			System.out.println("pass send");
			br.readLine();
			pw.println(mail);
			System.out.println("mail send");
			int r = Integer.parseInt(br.readLine());
			if(r == 0) {
				joined = 0;	//가입성공
			}else if(r == 1) {
				joined = 1;	//가입실패
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return joined;
	}
}
