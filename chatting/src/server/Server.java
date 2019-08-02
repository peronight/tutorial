package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	ServerSocket server = null;
	
	public void init() {
		try {
			server = new ServerSocket(5656);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
