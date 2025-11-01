package alarmevent;

import eventbroker.EventPublisher;
import network.Network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        System.out.println("Op welk nummer kan je dit callcentrum bereiken?");
        Scanner myObj = new Scanner(System.in);
        String nummer = myObj.nextLine();
        EmergencyCallCenter centrum = new EmergencyCallCenter(nummer);

        while(true){
            System.out.println("Wat is er gebeurd? (alarm)");
            String alarm = myObj.nextLine();
            System.out.println("Waar is het gebeurd? (locatie)");
            String location = myObj.nextLine();
            centrum.incomingCall(alarm,location);
            System.out.println("\n");
            TimeUnit.SECONDS.sleep(1);
        }

    }
    
}
