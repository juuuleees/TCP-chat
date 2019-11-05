import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.SocketAddress;

// handles a client's I/O streams
public class ClientStream {

	private String name;
	private SocketAddress remote_addr;
	private DataInputStream incoming;
	private DataOutputStream outgoing;

	// getters
	public String get_name() { return this.name; }
	public SocketAddress get_remote_addr() { return this.remote_addr; }
	public DataInputStream get_incoming() { return this.incoming; }
	public DataOutputStream get_outgoing() { return this.outgoing; }

	// setters
	public void set_name(String new_name) { this.name = new_name; }
	public void set_remote_addr(SocketAddress new_addr) { this.remote_addr = new_addr; }
	public void set_incoming(DataInputStream new_in_stream) { this.incoming = new_in_stream; }
	public void set_outgoing(DataOutputStream new_out_stream) { this.outgoing = new_out_stream; }

	public ClientStream(String name, SocketAddress addr, InputStream in, 
						OutputStream out) {

		this.name = name;
		this.remote_addr = addr;

		try {

			this.incoming = new DataInputStream(in);
			this.outgoing = new DataOutputStream(out);

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}

}