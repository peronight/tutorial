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
		if(list.list.contains(id)) {	//���ŵ� id�� ȸ�� ����Ʈ���� �ߺ� üũ
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
				if(r.equals("dupcheck")) {	//�ߺ�üũ ��û������
					System.out.println("�ߺ�üũ ����");
					pw = new PrintWriter(soc.getOutputStream(), true);
					pw.println();		//id���� �غ�
					System.out.println("id ���� �غ�");
					dupcheck(in.readLine());
				}else if(r.equals("join")){	//ȸ������ ��û������
					System.out.println("���� ����");
					join();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void join() {
		try {
			System.out.println("���� �޼��� �����");
			in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			pw = new PrintWriter(soc.getOutputStream(), true);
			pw.println();			//ȸ������ �غ��
			System.out.println("�غ� �Ϸ�");
			id = in.readLine();
			System.out.println("id����");
			pw.println();
			pass = in.readLine();
			System.out.println("pass����");
			pw.println();
			mail = in.readLine();
			System.out.println("mail����");
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
		System.out.println("���� ������ ����");
	}
	
	public static void main(String[] args) {
		Socket tmp = null;
		String tmp2 = null;
		MemberRegist mr = new MemberRegist(tmp, tmp2);
		mr.init();
	}
}
