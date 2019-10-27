import java.net.*;
import java.io.*;

public class ChatServer extends Thread {
	
	private ServerSocket server_socket;

	public ChatServer(int port) {
		server_socket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);
	}

	public void run() {
		boolean connected = true;

		/*
			Tinatry kong i-incorporate dito yung ginawa sa CircleWars
			na anim yung pwedeng mag-connect sa server bago magsara yung connection.
			Am having trouble atm kasi di ko makita kung pano inimplement ni Sir Jach.
		*/	
	}

}