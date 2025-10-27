package alarmevent;

import eventbroker.EventBroker;
import eventbroker.EventPublisher;
import network.Network;

import java.io.IOException;
import java.net.InetAddress;

public class AlarmCentrale  extends Client {
    private String emergencyNumber;

    public AlarmCentrale(String number) throws IOException {
        super();
        this.emergencyNumber = number;
        network.setServer();
    }

    public void incomingCall(String alarm, String location) {
        System.out.println("Incoming call on number " + emergencyNumber);
        AlarmEvent event = new AlarmEvent(alarm, location);
        network.connection.send(event);
    }
}
