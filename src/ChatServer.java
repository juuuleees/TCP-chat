// import java.net.*;
// import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.ServerSocket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class ChatServer extends Thread {
	
	private ServerSocket server_socket;
	// this next line is for testing
	private ArrayList<Socket> socket_connections = new ArrayList<Socket>();
	// private String[] names;
	// private HashMap<SocketAddress, String> connection_info = new HashMap<SocketAddress, String>();
	private ArrayList<ClientStream> client_streams = new ArrayList<ClientStream>();
	private String[] all_messages;
	private int total_clients;

	public ChatServer(int addr_port, int clients) {
		try {
			server_socket = new ServerSocket(addr_port);
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

		//  keep accepting connections as long as the room isn't full
		while (is_room_full != true) {
			
			try {	
			
				Socket new_client = server_socket.accept();

				DataInputStream incoming = new DataInputStream(new_client.getInputStream());
				String client_name = incoming.readUTF();
				System.out.println(client_name + " has connected to the server!");	
				ClientStream fresh_stream = new ClientStream(client_name, new_client.getRemoteSocketAddress(),
															   incoming, new_client.getOutputStream());


				socket_connections.add(new_client);
				client_streams.add(fresh_stream);	
				// connection_info.put(new_client.getRemoteSocketAddress(), client_name);

				if (socket_connections.size() == total_clients) {
					System.out.println("Chat room full!");
					// System.out.println(connection_info);
					// for (int i = 0; i < client_streams.size(); i++) {
					// 	System.out.println(client_streams.get(i).get_remote_addr());
					// }
					is_room_full = true;
				}
			
			} catch (Exception e) {
				System.out.println("Error: " + e);
				break;
			}	

		}

		// pass the messages to each client as they come
		// while (connected) {

		// }
	
	}

	public static void main(String args[]) {
		
		try {

			int port = Integer.parseInt(args[0]);
			int clients = Integer.parseInt(args[1]);
			Thread main_chat = new ChatServer(port, clients);
			main_chat.start();

		} catch (Exception e) {
			System.out.println("Server timed out. Goodbye.");
		}
	}

}