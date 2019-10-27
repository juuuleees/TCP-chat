import java.net.InetAddress;

public class Client {
	
	private INetAddress ip_addr;
	private int port;
	private String name;

	// constructor
	public Client(String name, INetAddress addr, int port) {
		this.name = name;
		this.ip_addr = addr;
		this.port = port;
	}

	// getters
	public String get_name() { return this.name; }
	public int get_port() { return this.port; }
	public INetAddress get_ip_address() { return this.ip_addr; }

	// setters
	public String set_name(String name) { this.name = name; }
	public int set_port(int port) { this.port = port; }
	public INetAddress set_ip_address(INetAddress ip) { this.ip_addr = ip; }

}