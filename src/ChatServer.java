import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
import java.util.Random;

public class ChatServer extends Thread {
	
	private ServerSocket server_socket;
	// this next line is for testing
	private ArrayList<Socket> test_mappings = new ArrayList<Socket>();
	// private HashMap<SocketAddress, String> client_mappings;
	private int total_clients;
	private int clients_connected = 0;

	public ChatServer(int port, int clients) {
		try {
			server_socket = new ServerSocket(port);
			this.total_clients = clients; 
			server_socket.setSoTimeout(10000);
			System.out.println("Server up!");
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	// getters
	// public 

	public void run() {
		boolean is_room_full = false;
		boolean connected = true;
		// Random rangen = new Random();

		System.out.println("Client list not yet full, waiting for further connections...");
		System.out.println("Waiting for clients on port " + server_socket.getLocalPort() + "...");	

		while (is_room_full != true) {
			
			try {	
			
				Socket new_client = server_socket.accept();
				// don't have the extra computers/VMs to test this properly
				// but this should show whether or not they've managed to connect to the server
				// System.out.println(new_client.getRemoteSocketAddress() + "has just connected!");
				System.out.println(new_client.getRemoteSocketAddress() + " has connected to the server!");	
				test_mappings.add(new_client);	
				clients_connected++;	

				if (clients_connected == test_mappings.size()) {
					System.out.println("Chat room full!");
					is_room_full = true;
				}
			
			} catch (Exception e) {
				System.out.println("Error: " + e);
				break;
			}	

		}

		// while (connected) {

		// 	// System.out.println(is_room_full);
		// 	// allow clients to connect to the server until full
		// 	if (is_room_full == false) {
		// 		System.out.println("Client list not yet full, waiting for further connections...");
		// 	// 	System.out.println("Waiting for clients on port " + server_socket.getLocalPort() + "...");
	
		// 		// try {
	
		// 		// 	Socket new_client = server_socket.accept();
		// 		// 	// don't have the extra computers/VMs to test this properly
		// 		// 	// but this should show whether or not they've managed to connect to the server
		// 		// 	// System.out.println(new_client.getRemoteSocketAddress() + "has just connected!");
		// 		// 	System.out.println(new_client.getRemoteSocketAddress() + " has connected to the server!");
	
		// 		// 	test_mappings.add(new_client);
	
		// 		// 	clients_connected++;
	
		// 		// } catch (Exception e) {
		// 		// 	System.out.println("Error: " + e);
		// 		// 	break;
		// 		// }
		// 	}

		// 	// while (is_room_full) {
		// 	// 	System.out.println("Client list not yet full, waiting for further connections...");
		// 	// 	System.out.println("Waiting for clients on port " + server_socket.getLocalPort() + "...");
	
		// 	// 	try {
	
		// 	// 		Socket new_client = server_socket.accept();
		// 	// 		// don't have the extra computers/VMs to test this properly
		// 	// 		// but this should show whether or not they've managed to connect to the server
		// 	// 		// System.out.println(new_client.getRemoteSocketAddress() + "has just connected!");
		// 	// 		System.out.println(new_client.getRemoteSocketAddress() + " has connected to the server!");
	
		// 	// 		test_mappings.add(new_client);
	
		// 	// 		clients_connected++;
	
		// 	// 	} catch (Exception e) {
		// 	// 		System.out.println("Error: " + e);
		// 	// 		break;
		// 	// 	}
	
		// 	// }

		// }

		// while (connected) {

			
				
			

		// }



		/*
			Tinatry kong i-incorporate dito yung ginawa sa CircleWars
			na anim yung pwedeng mag-connect sa server bago magsara yung connection.
			Am having trouble atm kasi di ko makita kung pano inimplement ni Sir Jach.
		*/	
	}

	public static void main(String args[]) {
		
		try {

			int port = Integer.parseInt(args[0]);
			int clients = Integer.parseInt(args[1]);
			Thread main_chat = new ChatServer(port, clients);
			main_chat.start();

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

}