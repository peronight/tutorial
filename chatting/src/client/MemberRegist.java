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
		System.out.println("�ߺ�üũ �޼��� ����");
		int r = 0;
		try {
			pw = new PrintWriter(soc.getOutputStream(), true);
			pw.println("dupcheck");	//�ߺ�üũ ��û����
			br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			System.out.println("id�۽� ���");
			br.readLine();			//id�۽� ���
			pw.println(id);
			System.out.println("id �۽� �Ϸ�");
			
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
			System.out.println("������ ����");
			br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			pw = new PrintWriter(soc.getOutputStream(), true);
			pw.println("join");
			br.readLine();			//������ ���� �غ� ��ȣ ���
			System.out.println("���� ������");
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
				joined = 0;	//���Լ���
			}else if(r == 1) {
				joined = 1;	//���Խ���
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return joined;
	}
}
