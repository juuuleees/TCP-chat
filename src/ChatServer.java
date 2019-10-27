import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Random;

public class ChatServer extends Thread {
	
	private ServerSocket server_socket;
	// this next line is for testing
	private HashMap<Integer, Socket> test_mappings;
	// private HashMap<SocketAddress, String> client_mappings;
	private int total_clients;
	private int clients_connected = 0;

	public ChatServer(int port, int clients) {
		server_socket = new ServerSocket(port);
		this.total_clients = clients; 
		server_socket.setSoTimeout(10000);
	}

	// getters
	// public 

	public void run() {
		boolean is_room_full = false;
		boolean connected = true;
		Random rangen = new Random();

		while (is_room_full) {
			System.out.println("Client list not yet full, waiting for further connections...");
			System.out.println("Waiting for clients on port " + server_socket.getLocalPort() + "...");

			try {

				Socket new_client = server_socket.accept();
				// don't have the extra computers/VMs to test this properly
				// but this should show whether or not they've managed to connect to the server
				// System.out.println(new_client.getRemoteSocketAddress() + "has just connected!");
				System.out.println(new_client.getRemoteSocketAddress() + " has connected to the server!");
				int hashmap_key = rangen.nextInt(total_clients);

				test_mappings.put(hashmap_key, new_client);

				clients_connected++;

			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out.");
				break;
			}

		}

		while (connected) {

			Iterator map_it = test_mappings.entrySet().iterator();

			while(map_it.hasNext()) {
				try {
					Map.Entry pair = (Map.Entry)map_it.next();
					Socket new_client = pair.getValue();

					DataInputStream in = new DataInputStream(new_client.getInputStream());
					System.out.println(in.readUTF());
	
					DataOutputStream out = new DataOutputStream(new_client.getOutputStream());
					out.writeUTF("Thank you for connecting to " + new_client.getLocalSocketAddress() + 
						", goodbye.");
	
					new_client.close();
					System.out.println("Server cut connection to " + new_client.getRemoteSocketAddress() + ".");
				} catch (SocketTimeoutException s) {
					System.out.println("Socket timed out.");
					break;
				}
			}

		}



		/*
			Tinatry kong i-incorporate dito yung ginawa sa CircleWars
			na anim yung pwedeng mag-connect sa server bago magsara yung connection.
			Am having trouble atm kasi di ko makita kung pano inimplement ni Sir Jach.
		*/	
	}

}