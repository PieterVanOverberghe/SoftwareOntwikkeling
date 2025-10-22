package parallelgreeter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        ServerSocket listen = null;
        int serverPort=1024;
        ArrayList<Draad> draden = new ArrayList<Draad>();
        try {
            listen = new ServerSocket(serverPort);

            while(true){
                Socket client =  listen.accept(); //maakt automatisch een nieuwe socket aan voor mij
                Draad d = new Draad(client);
                draden.add(d);
                Thread t = new Thread(d);
                t.start();
            }


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

    public static class Draad implements Runnable {
        protected Socket client;
        public Draad(Socket socket){
            this.client = socket;
        }

        @Override
        public void run() {
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                String name;
                while(true){
                    if (((name = in.readLine())==null)) break;
                    if(name.equals("stop")) break;
                    String rMess="Hello, "+ name;
                    out.println(rMess);
                }
            }catch (IOException e){
                e.printStackTrace();
            }

            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

