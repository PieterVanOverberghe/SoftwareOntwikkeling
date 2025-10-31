package alarmevent;

import alarmevent.AlarmEvent;
import eventbroker.EventBroker;
import network.Network;

import java.net.InetAddress;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        EventBroker.getEventBroker().start();

        PoliceDepartment police1 = new PoliceDepartment();
        PoliceDepartment police2 = new PoliceDepartment();
//        Hospital UZ = new Hospital("UZ");
        FireDepartment brandweer = new FireDepartment();

        EmergencyCallCenter center1 = new EmergencyCallCenter("112");
        EmergencyCallCenter center2 = new EmergencyCallCenter("101");



        center1.incomingCall("fire","veldstraat");
//        center2.incomingCall("crash","plateau");
//        center1.incomingCall("assault","fritzstraat");


        //hou het draaiende
        Thread.currentThread().join();
    }
}
