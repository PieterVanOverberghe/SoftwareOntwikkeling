package alarmevent;

import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class FireDepartment extends Client implements EventListener {

    public FireDepartment() throws UnknownHostException {
        InetAddress host = InetAddress.getLocalHost();
        super(host,1024);
        EventBroker.getEventBroker().addEventListener("fire",this);
    }
    
    @Override
    public void handleEvent(Event e) {
        if(e instanceof AlarmEvent){
            AlarmEvent alarm = (AlarmEvent) e;
            System.out.println("Fire squad on the move to "+alarm.getLocation()+" for "+alarm.getType());
        }
    }
    
}
