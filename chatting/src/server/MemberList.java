package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;

public class MemberList {
	HashSet<String> list = new HashSet<String>();
	File f = new File("members", "list.list");
	InputStream in = null;
	ObjectInputStream ois = null;
	OutputStream out = null;
	ObjectOutputStream oos = null;
	
	public void init() {
			try {
				if(f.exists()) {
					in = new FileInputStream(f);
					ois = new ObjectInputStream(in);
					list = (HashSet<String>)ois.readObject();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public synchronized void add(String id) {
		list.add(id);
		try {
			out = new FileOutputStream(f);
			oos = new ObjectOutputStream(out);
			oos.writeObject(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void del(String id) {
		list.remove(id);
		try {
			out = new FileOutputStream(f);
			oos = new ObjectOutputStream(out);
			oos.writeObject(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int existCheck(String id) {
		int r;
		if(list.contains(id)) {
			r = 1;	//id 있음
			System.out.println("id exist");
		}else {
			r = 0;	//id 없음
			System.out.println("id none");
		}
		return r;
	}
	public void print() {
		Iterator it = list.iterator();
		if(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
