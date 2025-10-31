package alarmevent;

import eventbroker.EventPublisher;
import network.Network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EmergencyCallCenter extends EventPublisher{
    protected Network network;
    InetAddress host;
    int serverport;
    
    private String emergencyNumber;
    
    public EmergencyCallCenter(String number) throws UnknownHostException {
        this.emergencyNumber = number;
        this.serverport = 1024;
        this.host = InetAddress.getLocalHost();

        network = new Network();
        network.connect(host,serverport,5);
    }
    
    public void incomingCall(String alarm, String location){
        System.out.println("Incoming call on number "+emergencyNumber);
        network.handleEvent(new AlarmEvent(alarm, location));
    }
    
}
