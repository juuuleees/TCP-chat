import java.net.InetAddress;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
// import java.util.Scanner;

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
		// Scanner text_in = new Scanner(System.in);
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
			BufferedReader from_server = new BufferedReader(new InputStreamReader(come_from_server));
			connected_to_server = true;

			// send username to server
			outgoing.writeUTF(name);

			// send/receive chat messages
			while (connected_to_server) {
				
				if (incoming.available() != 0) {
					System.out.println(from_server.readLine());
				} else { 
					System.out.print("> ");
					try {
						BufferedReader text_reader = new BufferedReader(new InputStreamReader(System.in));
						String out_text = text_reader.readLine();

						if (out_text.equals("bye")) {
							System.out.println("Leaving chat...");
							server.close();
							connected_to_server = false;
						} else {
							PrintWriter writer = new PrintWriter(send_to_server, true);
							writer.println(out_text);
						}

					} catch (Exception e) {
						System.out.println("Error: " + e);
					}

				}

				// thread implementation
				// try {
				// 	BufferedReader text_reader = new BufferedReader(new InputStreamReader(System.in));

				// 	new GetMessage(text_reader, incoming).start();
				// 	new SendMessage(send_to_server);

				// } catch (Exception e) {
				// 	System.out.println("Error: " + e);
				// }

			}


		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}

}