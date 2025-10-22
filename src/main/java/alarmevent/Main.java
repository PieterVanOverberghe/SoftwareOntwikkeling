package alarmevent;



public class Main {
    public static void main(String[] args) {
        PoliceDepartment police = new PoliceDepartment("1");
        PoliceDepartment police2 = new PoliceDepartment("2");
        FireDepartment brandweer1 =  new FireDepartment("1");
        Hospital ziekenhuis1 = new Hospital("Uz");

        EmergencyCallCenter callCenter = new EmergencyCallCenter();

        callCenter.publishEvent(new AlarmEvent("crash","Plateau"));
        callCenter.publishEvent(new AlarmEvent("fire","Sterre"));


    }
}
