package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ListService {
	Socket soc = null;
	ObjectOutputStream oos = null;
	
	public ListService(Socket soc) {
		try {
			this.soc = soc;
			oos = new ObjectOutputStream(this.soc.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendList(ArrayList<String> logList, ArrayList<String> roomList) {
		try {
			System.out.println(soc);
			System.out.println(logList);
			System.out.println(roomList);
			oos.writeObject(logList);
			oos.writeObject(roomList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
}
