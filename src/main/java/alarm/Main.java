package alarm;

public class Main {
    
    public static void main(String[] args){
        
        Hospital hospital1 = new Hospital("UZ");
        Hospital hospital2 = new Hospital("AZ");
        PoliceDepartment police = new PoliceDepartment();
        FireDepartment brandweer = new FireDepartment("Brandweer Lichtervelde");
        
        EmergencyCallCenter callCenter = new EmergencyCallCenter("112");
        EmergencyCallCenter callCenter2 = new EmergencyCallCenter("101");

        callCenter.addListener(hospital1);
        callCenter.addListener(hospital2);
        callCenter.addListener(police);
        callCenter.addListener(brandweer);

        callCenter2.addListener(police);
        
        callCenter.incomingCall("crash", "Plateaustraat");
        callCenter.incomingCall("assault", "Veldstraat");
        callCenter.incomingCall("fire", "Zwijnaardse Steenweg");

        callCenter2.incomingCall("riot","Fritz De Beulestraat");
        
    }
}
