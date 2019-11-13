import java.net.SocketAddress;


public class ChatMessage {
	
	private String message;
	private SocketAddress sender;

	// getters
	public String get_message() { return this.message; }
	public SocketAddress get_sender() { return this.sender; }

	// setters
	public void set_message(String new_msg) { this.message = new_msg; }
	public void set_sender(SocketAddress new_sender) { this.sender = new_sender; }

	public ChatMessage(String msg, SocketAddress sender) {
		this.message = msg;
		this.sender = sender;
	}
	
}