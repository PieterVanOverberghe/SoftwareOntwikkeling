package alarmevent;

import eventbroker.EventBroker;
import network.Network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {
    protected Network network;
    InetAddress host;
    int serverport;

    public Client(InetAddress host, int serverport) throws UnknownHostException {
        this.serverport = serverport;
        this.host = host;

        network = new Network();
        network.connect(host,serverport);
    }

}
