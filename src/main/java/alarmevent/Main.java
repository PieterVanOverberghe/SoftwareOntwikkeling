package alarmevent;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AlarmCentrale centrale1 = new AlarmCentrale("112");
//        AlarmCentrale centrale2 = new AlarmCentrale("101");
        centrale1.incomingCall("crash","Plateaustraat");
//        centrale2.incomingCall("assault","Veldstraat");
        centrale1.incomingCall("fire", "Zwijnaardse Steenweg");
//        centrale2.incomingCall("assault","Overpoortstraat");


    }
}
