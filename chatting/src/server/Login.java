package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
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
	DataInputStream dis = null;
	File f = null;
	
	public Login(Socket soc) {
		this.soc = soc;
	}
	
	public void login() {
		try {
			System.out.println("login execute");
			MemberList ml = new MemberList();
			ml.init();
			br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			pw = new PrintWriter(soc.getOutputStream(), true);
			pw.println();	//클라이언트에 준비됨 알림
			id = br.readLine();
			r = ml.existCheck(id);
			pw.println(r);
			if(r == 0) {
				
			}else if(r == 1) {
				f = new File("members", id + ".info");
				dis = new DataInputStream(new FileInputStream(f));
				dis.readUTF();	//저장된 회원정보의 id열을 먼저 읽어서 다음열인 pass 읽어들일 준비
				pass = br.readLine();
				if(pass.equals(dis.readUTF())) {
					pw.println("1");	//client 의 passok 가 받음
					r = 3;	//main에 전달하기 위한 로그인 성공값 준비
				}else {
					pw.println("0");	//client 의 passok 가 받음
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
