package server;

import java.util.ArrayList;

public class LoginList {
	ArrayList<String> list;
	
	public LoginList() {
		System.out.println("LoginList ������ ����");
		list = new ArrayList<String>();
	}
	
	public synchronized void add(String id) {
		if(!list.contains(id)) {
			list.add(id);
		}
	}
	
	public synchronized void del(String id) {
		if(list.contains(id)) {
			list.remove(id);
		}
	}
}
