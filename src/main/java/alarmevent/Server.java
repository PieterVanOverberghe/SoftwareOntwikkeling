package alarmevent;

import eventbroker.EventBroker;
import network.Network;

public class Server {
    public static void main(String[] args) throws InterruptedException {
        EventBroker.getEventBroker().start();
        Network network = new Network(1024);
        System.out.println("server gestart");

        //zorg dat het blijft draaien
        Thread.currentThread().join();
        System.out.println("server gestopt");
    }
}
