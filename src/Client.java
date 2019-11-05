import java.net.InetAddress;
import java.net.Socket;


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

		try {
	
			System.out.println("Connecting to " + addr + " on port " + port);
			Socket server = new Socket(addr, port);
			System.out.println("Just connected to " + server.getRemoteSocketAddress());

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}

}