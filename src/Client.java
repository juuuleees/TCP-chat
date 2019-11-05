import java.net.InetAddress;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Client {
	
	private InetAddress ip_addr;
	private int port;
	private String name;

	// constructor
	public Client(String name, InetAddress addr, int port) {
		this.name = name;
		this.ip_addr = addr;
		this.port = port;
	}

	// getters
	public String get_name() { return this.name; }
	public int get_port() { return this.port; }
	public InetAddress get_ip_address() { return this.ip_addr; }

	// setters
	public void set_name(String name) { this.name = name; }
	public void set_port(int port) { this.port = port; }
	public void set_ip_address(InetAddress ip) { this.ip_addr = ip; }

	public static void main(String args[]) {

		String name = args[0];
		String addr = args[1];
		int port = Integer.parseInt(args[2]);
		Scanner text_in = new Scanner(System.in);
		boolean connected_to_server;

		try {
	
			System.out.println("Connecting to " + addr + " on port " + port);
			Socket server = new Socket(addr, port);
			System.out.println("Just connected to " + server.getRemoteSocketAddress());

			// set up input and output streams
			OutputStream send_to_server = server.getOutputStream();
			DataOutputStream outgoing = new DataOutputStream(send_to_server);

			InputStream come_from_server = server.getInputStream();
			DataInputStream incoming = new DataInputStream(come_from_server);
			connected_to_server = true;

			// send username to server
			outgoing.writeUTF(name);

			// send/receive chat messages
			while (connected_to_server) {
				System.out.print("> ");
				String msg_text = text_in.next();
				
				if (msg_text.equals("bye")) {
					System.out.println("Leaving chat...");
					server.close();
					connected_to_server = false;
				} else {
					outgoing.writeUTF(msg_text);
				}

			}


		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}

}