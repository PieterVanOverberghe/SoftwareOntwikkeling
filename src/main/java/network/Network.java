package network;

import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;
import eventbroker.EventPublisher;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Network extends EventPublisher implements EventListener {
    protected EventBroker eventbroker;
    protected int serverport;
    protected List<Connection> connections = new ArrayList<>();
    public Connection connection;
    protected ConnectionListener listener;
    protected boolean isserver;
    protected boolean ontvangenDitNetwerk = false; //hebben we het huidige event van dit netwerk ontvangen of niet => oneindige lus vermijden

    //als je het start als server
    public Network(int serverport) throws IOException {
        this.serverport = serverport;
        this.eventbroker = EventBroker.getEventBroker();
        eventbroker.addEventListener(this);
        eventbroker.start();
        isserver = true;

        listener = new ConnectionListener(this,serverport);
        listener.start();
    }

    public void setServer(){
        isserver = true;
    }

    //als je het start als client
    public Network(){
        this.eventbroker = EventBroker.getEventBroker();
        eventbroker.addEventListener(this);
        isserver = false;
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
            connections.add(connection);
            connection.receive();
            return connection;

        }catch(IOException e){
            e.printStackTrace();
            return null;
        }

    }



    @Override
    public void handleEvent(Event e) {
        System.out.println("handle event opgeroepen");
        if(ontvangenDitNetwerk) return;
        if(isserver){
            for(Connection c: connections){
                connection.send(e);
            }
        }
        else{
            if (connection != null) {
                connection.send(e);
            }
        }
    }

    //stopt alle draden
    public void terminate(){
        try{
            if(listener!=null){listener.terminate();}
            for(Connection c: connections){
                c.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
