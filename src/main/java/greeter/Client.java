package greeter;
import java.net.*;
import java.io.*;

public class Client {
	public static void main(String[] args) {
		InetAddress host = null;
		int serverPort = 1024;
		Socket socket = null;
		BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
		String name = "";
		try {

			host = InetAddress.getLocalHost();
			socket = new Socket(host,1024);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

			do {
				// read in a name
				System.out.println("Enter a name : ");
				consoleInput = new BufferedReader(new InputStreamReader(System.in));
				name = consoleInput.readLine();

				if (!(name.equals("stop"))) {
					// send the name
                    out.println(name);

					// receive reply
                    String ontvangen;
                    ontvangen = in.readLine();
					// print the greeting
					System.out.println("Reply from server = " + ontvangen);

				}
			} while (!(name.equals("stop")));
		} catch (UnknownHostException e) {
			System.err.println(e);
		} catch (SocketException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (socket != null)
                try{
				socket.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
		}
	}
}

