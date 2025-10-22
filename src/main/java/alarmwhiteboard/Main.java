package alarmwhiteboard;
import alarm.PoliceDepartment;
import alarm.FireDepartment;
import alarm.Hospital;

public class Main {
    public static void main(String[] args){
        PoliceDepartment politie1 = new PoliceDepartment();
        PoliceDepartment politie2 = new PoliceDepartment();

        Hospital ziekenhuis1 = new Hospital("UZ");
        Hospital ziekenhuis2 = new Hospital("Jan Palfijn ziekenhuis");

        FireDepartment brandweer1 = new FireDepartment("BW Gent");
        FireDepartment brandweer2 = new FireDepartment("BW Ledeberg");

        EmergencyCallCenter callCenter = new EmergencyCallCenter("15");

        Whiteboard whiteboard = Whiteboard.GetWhiteboard();

        whiteboard.addAlarmListener(politie1);
        whiteboard.addAlarmListener(politie2);

        whiteboard.addAlarmListener("fire",brandweer1);
        whiteboard.addAlarmListener("fire",brandweer2);

        whiteboard.addAlarmListener("fire",ziekenhuis1);
        whiteboard.addAlarmListener("crash",ziekenhuis1);

        whiteboard.addAlarmListener("fire",ziekenhuis2);
        whiteboard.addAlarmListener("crash",ziekenhuis2);

        callCenter.incomingCall("fire","debeulestraat");
        System.out.println();
        callCenter.incomingCall("crash","sterre");
        System.out.println();
        callCenter.incomingCall("assault","plateau");
        System.out.println();
        callCenter.incomingCall("fire","debeulestraat");
        System.out.println();
        callCenter.incomingCall("crash","sterre");
        ;

    }
}
