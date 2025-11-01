package network;

import alarmevent.AlarmEvent;
import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;
import eventbroker.EventPublisher;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Network extends EventPublisher implements EventListener {
    private static int nextId = 1;  // static counter for all Network instances
    public final int networkId;     // unique ID for this instance
    protected EventBroker eventbroker;
    protected int serverport;
    protected Connection connection;
    protected ConnectionListener listener;
    protected final Set<Connection> connections = Collections.synchronizedSet(new HashSet<>());

    //als je het start als server
    public Network(int serverport){
        this.networkId = nextId++;
        this.serverport = serverport;
        this.eventbroker = EventBroker.getEventBroker();
        eventbroker.addEventListener(this);


        listener = new ConnectionListener(this,serverport);
        listener.start();
    }

    //als je het start als client
    public Network(){
        this.networkId = nextId++;
        this.eventbroker = EventBroker.getEventBroker();
        eventbroker.addEventListener(this);
    }

    //als client verbinden met een server
    public Connection connect(InetAddress address, int port){
        try{
            Socket socket = new Socket(address,port);
            connection = new Connection(socket,this);
            connections.add(connection);
            connection.receive();
            return connection;

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    //enkel callcenters mogen deze oproepen
    public Connection connect(InetAddress address, int port, int i){
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
        if (connection != null) {
                connection.send(e);
        }
    }

    public void removeConnection(Connection c){
        connections.remove(c);
    }

    // aangeroepen wanneer een connectie een event heeft ontvangen van het netwerk
    public void publishFromConnection(Event e, Connection from) {
        Event eventToSend = e;


        //eigenlijk zorg ik er gwn voor dat als het voor de eerste keer hier komt(dat is in server) dat hij dan de event "merkt" en dat al de rest ziet dat hij al eens geforward is naar alle connecties via server
        if (e instanceof AlarmEvent alarm) {
            if (alarm.getOriginId() == null) {
                eventToSend = new AlarmEvent(alarm.getType(), alarm.getLocation(), this.networkId);
            } else {
                publishEvent(eventToSend);
                return;
            }
        }



        synchronized(connections){
            for(Connection c : connections){
                if(c != from){
                    c.send(eventToSend);
                }
            }
        }
    }



    //stopt alle draden
    public void terminate(){
        try{
            if(listener!=null){
                listener.terminate();
            }
            synchronized(connections){
                for(Connection c : connections){
                    try{ c.close(); } catch(Exception ex){}
                }
                connections.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
