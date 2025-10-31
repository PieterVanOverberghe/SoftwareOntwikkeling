package alarmevent;

import eventbroker.Event;
import network.Network;

import java.io.Serializable;
import java.util.UUID;

public class AlarmEvent extends Event implements Serializable{
//    public transient Network origin= null; //werkte niet want network is niet serializable, dus kan je niet mee versturen

    public  Integer origin;
    private final String location;

    public AlarmEvent(String type, String location){
        super(type, type+" at "+location);
        this.location = location;
        this.origin = null;
    }


    public AlarmEvent(String type, String location,Integer originid){
        super(type, type+" at "+location);
        this.location = location;
        this.origin = originid;
    }


    
    public String getLocation(){
        return location;
    }
    public Integer getOriginId(){return origin;}
    public void setOriginId(Integer originId) {
        this.origin = originId;
    }
}
