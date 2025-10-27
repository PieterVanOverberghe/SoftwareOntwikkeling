package alarmevent;

import java.io.*;
import java.net.InetAddress;

import eventbroker.EventBroker;
import network.Network;
import eventbroker.Event;

public class Client {
    protected Network network;
    protected EventBroker eventbroker;

    public Client(){
        eventbroker = EventBroker.getEventBroker();
        eventbroker.start();

        try{
            network = new Network();
            InetAddress host = InetAddress.getLocalHost();
            network.connect(host,1024);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void handleEvent(Event e){

    }
}
