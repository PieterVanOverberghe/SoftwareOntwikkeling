package alarmevent;

import eventbroker.EventBroker;
import eventbroker.EventPublisher;
import eventbroker.Event;


public class EmergencyCallCenter extends EventPublisher {

    public void incomingCall(AlarmEvent alarmEvent){
        publishEvent(alarmEvent);
    }
}
