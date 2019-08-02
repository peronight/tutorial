package server;

import java.util.ArrayList;

public class RoomList {
	ArrayList<String> list;
	
	public RoomList() {
		list = new ArrayList<String>();
	}
	
	public void add(String room) {
		list.add(room);
	}
	
	public void del(String room) {
		if(list.contains(room)) {
			list.remove(room);
		}
	}
}
