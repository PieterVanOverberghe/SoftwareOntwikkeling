package serialization;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        try{
            InetAddress host = InetAddress.getLocalHost();
            Socket client = new Socket(host,1024);
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            Person p = new Person("Giovanni","Totti");


//            System.out.println(p);
            out.writeObject(p);
            Person ontvangen = (Person) in.readObject();
            System.out.println(ontvangen);


        }catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
