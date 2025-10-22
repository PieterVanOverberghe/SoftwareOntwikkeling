package alarm;

public class FireDepartment implements AlarmListener{
    private String name;
    public FireDepartment(String name) {
        this.name = name;
    }

    public void alarm(Alarm alarm){
        System.out.println("Fire Department - " + name + " is onderweg naar " + alarm.getLocation() + " om " + alarm.getType() +" op te lossen");
    }
}
