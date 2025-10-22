package network;

import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;
import eventbroker.EventPublisher;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Network extends EventPublisher implements EventListener {
    protected EventBroker eventbroker;
    protected int serverport;
    protected Connection connection;
    protected ConnectionListener listener;

    //als je het start als server
    public Network(int serverport){
        this.serverport = serverport;
        this.eventbroker = EventBroker.getEventBroker();
        eventbroker.addEventListener(this);


        listener = new ConnectionListener(this,serverport);
        listener.start();
    }

    //als je het start als client
    public Network(){
        this.eventbroker = EventBroker.getEventBroker();
        eventbroker.addEventListener(this);
    }

    //als client verbinden met een server
    public Connection connect(InetAddress address, int port){
        try{
            Socket socket = new Socket(address,port);
            connection = new Connection(socket,this);
            connection.receive();
            return connection;

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


    //als server accepteren van verbinding
    public Connection connect(Socket socket){
        try{
            connection = new Connection(socket,this);
            connection.receive();
            return connection;

        }catch(IOException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void handleEvent(Event e) {
        if(connection!=null){
            connection.send(e);
        }
    }

    //stopt alle draden
    public void terminate(){
        try{
            if(listener!=null){listener.terminate();}
            if(connection!=null){connection.close();}
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
