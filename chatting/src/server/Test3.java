package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Test3 {
	public static void main(String[] args) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		ServerSocket server = new ServerSocket(5454);
		Socket soc = server.accept();
		for(int i = 0; i < 1000000000; i++);
		ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
		oos.writeObject(list);
		System.out.println("list sent");
	}
}
