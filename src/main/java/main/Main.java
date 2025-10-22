
package main;

import eventbroker.EventBroker;
import order.BlacklistOrderProcessor;
import order.Customer;
import order.OrderProcessor;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main {
    
    public static int noOrders = 1000;

    public static void main(String[] args){
        EventBroker broker = EventBroker.getEventBroker();
        String[] names = new String[]{"Jan", "Piet", "Joris", "Corneel"};


        //kies order of blacklist
//        OrderProcessor orderProcessor = OrderProcessor.create();
        BlacklistOrderProcessor blps = BlacklistOrderProcessor.create();
        ExecutorService threadpool = Executors.newFixedThreadPool(names.length);
//        ArrayList<Thread> threads = new ArrayList<>();

        broker.start();
        for(String name : names){
//            Customer customer = new Customer(name);
//            Thread t = new Thread(customer);
//            threads.add(t);
            threadpool.execute(new Customer(name));
        }

        threadpool.shutdown();
        try{
            threadpool.awaitTermination(1, TimeUnit.MINUTES);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

//        for(Thread t : threads){
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        broker.stop();

        System.out.println("Totaal aantal orders verwerkt: " + OrderProcessor.getNumberOfOrders());


    }

    
}
