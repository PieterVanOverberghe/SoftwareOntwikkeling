package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// listens for incoming connections
public class ConnectionListener extends Thread {
    protected Network network;
    protected int serverport;
    protected boolean running = true;
    protected ServerSocket serversocket;

    public ConnectionListener(Network network, int serverport){
        this.network = network;
        this.serverport = serverport;

    }

    public void terminate(){
        running = false;
        try{
            serversocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        try{
            serversocket = new ServerSocket(serverport);
            while(running){
                Socket socket = serversocket.accept();
                network.connect(socket); //maak connectie aan
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
