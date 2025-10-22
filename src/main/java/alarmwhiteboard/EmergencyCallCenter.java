package alarmwhiteboard;
import alarm.Alarm;
import alarm.AlarmListener;
import java.util.Set;

public class EmergencyCallCenter {
    private String number;

    EmergencyCallCenter(String number) {
        this.number = number;
    }

    public void incomingCall(String alarm,String location){
        Set<AlarmListener> luisteraars =  Whiteboard.GetWhiteboard().getAlarmListeners(alarm);
//        System.out.println("Callcentrum " + number +" krijgt een alarm binnen over een "+ alarm +" op " + location);
        for (AlarmListener l : luisteraars){
            l.alarm(new Alarm(alarm,location));
        }
    }
}
