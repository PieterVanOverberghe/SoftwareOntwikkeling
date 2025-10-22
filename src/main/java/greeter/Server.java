package greeter;
import java.net.*;
import java.io.*;

public class Server {
	 public static void main(String[] args) {
	      ServerSocket listen = null;
	      int serverPort=1024;
	      try {
	    	  listen = new ServerSocket(serverPort);
              Socket client =  listen.accept();
              BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
              PrintWriter out = new PrintWriter(client.getOutputStream(), true);

              String name;
	    	  while((name = in.readLine())!=null) {
                  if(name.equals("stop")) {
                      break;
                  }
	    		  String rMess="Hello, "+ name;
	    		  out.println(rMess);
	    	  }
              client.close();

	      } catch(SocketException e) {
	    	  System.err.println(e);
	      } catch(IOException e) {
	    	  System.err.println(e);
	      } finally {
	    	  if(listen!=null) try{
                  listen.close();
              }catch(IOException e) {
                  e.printStackTrace();
              }
	      }
     }
}
