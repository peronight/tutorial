package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Test {
	public static void main(String[] args) throws IOException {
		InetAddress ia = InetAddress.getByName("127.0.0.1");
		Socket soc = new Socket(ia, 5656);
	}
}
