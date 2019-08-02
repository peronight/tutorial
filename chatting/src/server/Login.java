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
			pw.println();	//Ŭ���̾�Ʈ�� �غ�� �˸�
			id = br.readLine();
			r = ml.existCheck(id);
			pw.println(r);
			if(r == 0) {
				
			}else if(r == 1) {
				f = new File("members", id + ".info");
				dis = new DataInputStream(new FileInputStream(f));
				dis.readUTF();	//����� ȸ�������� id���� ���� �о �������� pass �о���� �غ�
				pass = br.readLine();
				if(pass.equals(dis.readUTF())) {
					pw.println("1");	//client �� passok �� ����
					r = 3;	//main�� �����ϱ� ���� �α��� ������ �غ�
				}else {
					pw.println("0");	//client �� passok �� ����
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
