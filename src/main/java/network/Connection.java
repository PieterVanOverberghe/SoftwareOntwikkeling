package network;

import eventbroker.Event;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    protected Socket socket;
    protected ObjectOutputStream out;
    protected ObjectInputStream in;
    protected Network network;
    protected ReceiverThread receiver;


    public Connection(Socket socket, Network network) throws IOException {
        this.socket = socket;
        this.network = network;
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
    }

    //stuur event via het netwerk
    public void send(Event e){
        try{
            out.writeObject(e);
            out.flush();
        } catch (IOException ex) {
            network.removeConnection(this);
            System.out.println("er ging iets fout bij get versturen van een event in connection");
            try{ close(); } catch(Exception e2){e2.printStackTrace(); }
            throw new RuntimeException(ex);
        }
    }


    public void receive(){
        receiver = new ReceiverThread();
        receiver.start();

    }

    public void close() throws IOException {
        if(receiver != null){
            receiver.running = false;
            in.close();
            out.close();
            socket.close();
            network.removeConnection(this);
        }

    }

    protected class ReceiverThread extends Thread{
        protected boolean running = true;

        public ReceiverThread(){
//            System.out.println("receiverthread aangemaakt");
        }

        public void run(){
            try{
                while(running){
                    Event e = (Event) in.readObject();
                    if(e == null) continue;
                    network.publishFromConnection(e,Connection.this);
                }
            } catch (IOException e) {
                running = false;
                System.out.println("er ging iets mis in receiverthread en nu staat hij uit");
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                running = false;
                System.out.println("er ging iets mis in receiverthread en nu staat hij uit");
                throw new RuntimeException(e);
            }
        }
    }
}
