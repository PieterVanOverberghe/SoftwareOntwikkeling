package alarmevent;

import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class PoliceDepartment extends Client implements EventListener {

    Random r = new Random();
    protected String naam;
    
    public PoliceDepartment(String naam) throws UnknownHostException {
        InetAddress host = InetAddress.getLocalHost();
        super(host,1024);
        EventBroker.getEventBroker().addEventListener(this);
        this.naam = naam;
    }
    
    @Override
    public void handleEvent(Event e) {
        if(e instanceof AlarmEvent){
            AlarmEvent alarm = (AlarmEvent) e;
            System.out.println("Police unit "+naam +" "+r.nextInt(10)+" is checking out the "+alarm.getType()+" at "+alarm.getLocation());
        }
    }

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        System.out.println("geef de naam van je politiekantoor");
        Scanner myObj = new Scanner(System.in);
        String naam = myObj.nextLine();
        PoliceDepartment police = new PoliceDepartment(naam);

        Thread.currentThread().join();
        System.out.println("politie gestopt");
    }
    
}
