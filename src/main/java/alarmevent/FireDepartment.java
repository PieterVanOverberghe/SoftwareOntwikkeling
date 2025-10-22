package alarmevent;

import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;

public class FireDepartment implements EventListener {
    private String naam;

    public FireDepartment(String naam) {
        this.naam = naam;
        EventBroker.getEventBroker().addEventListener("fire",this);


    }

    public void handleEvent(Event alarm){
        System.out.println("Fire Department  " +naam +" is checking out the " +alarm.getType()+" at "+ ((AlarmEvent) alarm).getLocatie() + " and sends an squad");
    }
}
