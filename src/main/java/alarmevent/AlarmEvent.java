package alarmevent;
import eventbroker.Event;

public class AlarmEvent extends Event {
    public String locatie;

    public AlarmEvent(String type, String locatie){
        super(type,"ALARM");
        this.locatie = locatie;
    }

    public String getLocatie(){
        return locatie;
    }


}
