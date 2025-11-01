package alarmevent;

import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class FireDepartment extends Client implements EventListener {

    protected String naam;

    public FireDepartment(String naam) throws UnknownHostException {
        InetAddress host = InetAddress.getLocalHost();
        super(host,1024);
        this.naam = naam;
        EventBroker.getEventBroker().addEventListener("fire",this);
    }
    
    @Override
    public void handleEvent(Event e) {
        if(e instanceof AlarmEvent){
            AlarmEvent alarm = (AlarmEvent) e;
            System.out.println("Fire squad "+ naam +" on the move to "+alarm.getLocation()+" for "+alarm.getType());
        }
    }
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        Scanner myObj = new Scanner(System.in);
        String naam = myObj.nextLine();
        FireDepartment fireDepartment = new FireDepartment(naam);

        Thread.currentThread().join();
        System.out.println("Brandweer gestopt");
    }
    
}
