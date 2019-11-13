
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class SendMessage {
	
	private PrintWriter message_writer;
	private OutputStream out_source;
	private String message_to_send;

	public SendMessage(OutputStream source) {
		this.message_writer = new PrintWriter(source, true);
		this.out_source = source;
	}

	public void run() {

		do {

			try {

				BufferedReader user_input = new BufferedReader(new InputStreamReader(System.in));
				message_to_send = user_input.readLine();

				message_writer.println(message_to_send);

			} catch (Exception e) {
				System.out.println("Error: " + e);
			}

		} while (!message_to_send.equals("bye"));
	}

}