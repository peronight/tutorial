package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Test3 {
	public static void main(String[] args) throws IOException {
		ArrayList<String> list = null;
		InetAddress ia = InetAddress.getByName("127.0.0.1");
		Socket soc = new Socket(ia, 5454);
		System.out.println("socket created");
		InputStream is = soc.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);
		try {
			list = (ArrayList<String>)ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list);
	}
}
