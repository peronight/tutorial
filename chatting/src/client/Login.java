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
			br.readLine();	//���� �α��� �޼��� ���� ���
			pw.println(id);
			r = Integer.parseInt(br.readLine());
			if(r == 0) {	//id ����
				
			}else if(r == 1) {	//id ����
				pw.println(pass);
				int passok = Integer.parseInt(br.readLine());
				if(passok == 1) {
					r = 2;	//pass ��ġ
					pw.println(id);	//server loginlist add�� id ����
					int d = Integer.parseInt(br.readLine());	//�ߺ����� Ȯ�ΰ� ����
					if(d == 1) {	//�ߺ������϶�
						r = 4;
					}
				}else if(passok == 0) {
					r = 3;	//pass ����ġ
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
