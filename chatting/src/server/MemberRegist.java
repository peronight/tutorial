package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MemberRegist {
	MemberList list = new MemberList();
	File f;
	String id, pass, mail;
	Socket soc = null;
	int dupcheck;
	PrintWriter pw;
	BufferedReader in;
	DataOutputStream dos;
	String r;
	
	public MemberRegist(Socket soc, String r) {
		this.soc = soc;
		this.r = r;
	}

	public void dupcheck(String id) {
		if(list.list.contains(id)) {	//수신된 id를 회원 리스트에서 중복 체크
			dupcheck = 1;
		}else {
			dupcheck = 0;
		}
		try {
			pw = new PrintWriter(soc.getOutputStream(), true);
			pw.println(dupcheck);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		list.init();
			try {
				in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				if(r.equals("dupcheck")) {	//중복체크 요청받을시
					System.out.println("중복체크 실행");
					pw = new PrintWriter(soc.getOutputStream(), true);
					pw.println();		//id수신 준비
					System.out.println("id 수신 준비");
					dupcheck(in.readLine());
				}else if(r.equals("join")){	//회원가입 요청받을시
					System.out.println("가입 실행");
					join();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void join() {
		try {
			System.out.println("가입 메서드 실행됨");
			in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			pw = new PrintWriter(soc.getOutputStream(), true);
			pw.println();			//회원가입 준비됨
			System.out.println("준비 완료");
			id = in.readLine();
			System.out.println("id수신");
			pw.println();
			pass = in.readLine();
			System.out.println("pass수신");
			pw.println();
			mail = in.readLine();
			System.out.println("mail수신");
			f = new File("members", id + ".info");
			dos = new DataOutputStream(new FileOutputStream(f));
			dos.writeUTF(id);
			dos.writeUTF(pass);
			dos.writeUTF(mail);
			list.add(id);
			int r = 0;
			pw.println(r);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				pw = new PrintWriter(soc.getOutputStream(), true);
				int r = 1;
				pw.println(r);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("가입 쓰레드 종료");
	}
	
	public static void main(String[] args) {
		Socket tmp = null;
		String tmp2 = null;
		MemberRegist mr = new MemberRegist(tmp, tmp2);
		mr.init();
	}
}
