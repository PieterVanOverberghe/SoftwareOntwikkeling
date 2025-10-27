
package alarmevent;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import eventbroker.EventBroker;
import network.Connection;
import network.ConnectionListener;
import network.Network;


public class Server {

    public static void main(String[] args) throws IOException{
        EventBroker eventBroker = EventBroker.getEventBroker();
        eventBroker.start();

        Network network = new Network(1024);

        FireDepartment brandweer = new FireDepartment();
        FireDepartment brandweer2 = new FireDepartment();

        PoliceDepartment politie = new PoliceDepartment();
//        Hospital ziekenhuis1 = new Hospital("UZ");
//        Hospital ziekenhuis2 = new Hospital("AZ");
        System.out.println("server gestart");



    }

}
