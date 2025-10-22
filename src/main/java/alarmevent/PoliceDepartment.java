package alarmevent;

import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;

public class PoliceDepartment implements EventListener {
    private String naam;

    public PoliceDepartment(String naam) {
        this.naam = naam;
        EventBroker.getEventBroker().addEventListener(this);
    }

    public void handleEvent(Event alarm){
        System.out.println("Police unit " +naam +" is checking out the " +alarm.getType()+" at "+ ((AlarmEvent) alarm).getLocatie());
    }
}
