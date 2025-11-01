package alarmevent;

import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class Hospital extends Client  implements EventListener {

    private String name;
    
    public Hospital(String name) throws UnknownHostException {
        InetAddress host = InetAddress.getLocalHost();
        super(host,1024);
        this.name = name;
        EventBroker.getEventBroker().addEventListener("fire",this);
        EventBroker.getEventBroker().addEventListener("crash",this);
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public void handleEvent(Event e) {
        if(e instanceof AlarmEvent){
            AlarmEvent alarm = (AlarmEvent) e;
            System.out.println(name+" sends an ambulance to "+alarm.getLocation()+" for "+alarm.getType());
        }
    }

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        System.out.println("geef de naam van je ziekenhuis");
        Scanner myObj = new Scanner(System.in);
        String naam = myObj.nextLine();
        Hospital hospital = new Hospital(naam);

        Thread.currentThread().join();
        System.out.println("ziekenhuis gestopt");

    }
    
}
