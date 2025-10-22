package alarmevent;

import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;

public class Hospital implements EventListener {
    private String naam;

    public Hospital(String naam) {
        this.naam = naam;
        EventBroker.getEventBroker().addEventListener("fire",this);
        EventBroker.getEventBroker().addEventListener("crash",this);

    }

    public void handleEvent(Event alarm){
        System.out.println("Hospital " +naam +" is checking out the " +alarm.getType()+" at "+ ((AlarmEvent) alarm).getLocatie() + " and sends an ambulance");
    }
}
