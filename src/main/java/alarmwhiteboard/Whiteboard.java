package alarmwhiteboard;

import alarm.AlarmListener;
import alarm.Hospital;
import eventbroker.EventBroker;
import eventbroker.EventListener;

import java.util.*;

final public class Whiteboard {
    Set<AlarmListener> luisteraars = new HashSet<AlarmListener>();
    ArrayList<Hospital> ziekenhuizen = new ArrayList<>();
    private static int ziekenhuisindex = 0;
    protected Map<String, Set<AlarmListener>> specifiekeListeners = new HashMap<>();
    protected final static Whiteboard bord = new Whiteboard();

    private Whiteboard() {}
    public static Whiteboard GetWhiteboard(){
        return bord;
    }

    public void addAlarmListener(AlarmListener l) {
        luisteraars.add(l);
    }

    public void addAlarmListener(String type, AlarmListener l) {
        specifiekeListeners.putIfAbsent(type, new HashSet<>());
        specifiekeListeners.get(type).add(l);
        if(l.getClass().getSimpleName().equals("Hospital")){
            ziekenhuizen.add((Hospital) l);
        }
    }

    public void removeAlarmListener(String type, AlarmListener l) {
        luisteraars.remove(l);
        specifiekeListeners.get(type).remove(l);
        if(l.getClass().getSimpleName().equals("Hospital")){
            ziekenhuizen.remove((Hospital) l);
            ziekenhuisindex = ziekenhuisindex %ziekenhuizen.size();
        }
    }

    public Set<AlarmListener> getAlarmListeners(String type){
        Set<AlarmListener> uitvoer = new HashSet<>(luisteraars);

        if (specifiekeListeners.containsKey(type)){
            for(AlarmListener l : specifiekeListeners.get(type)){
                if (l.getClass().getSimpleName().equals("Hospital")){
                    uitvoer.add(ziekenhuizen.get(ziekenhuisindex));
                    ziekenhuisindex = (ziekenhuisindex + 1)%ziekenhuizen.size();
                }
                else{
                uitvoer.add(l);
                }
            }
        }
        return uitvoer;
    }

}
