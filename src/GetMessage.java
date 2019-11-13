// import java.net.Socket;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;

public class GetMessage extends Thread {
	
	private BufferedReader text_reader;
	private DataInputStream message_sensor;

	public GetMessage(BufferedReader reader, DataInputStream sensor) {
		this.text_reader = reader;
		this.message_sensor = sensor;
	}

	public void run() {
		while (true) {
			try {
				if (message_sensor.available() != 0) {
					System.out.println(text_reader.readLine());
				}
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
	}

}