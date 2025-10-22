package eventbroker;

import java.util.*;

final public class EventBroker implements Runnable{

    protected Set<EventListener> listeners = new HashSet<EventListener>();
    protected final static EventBroker broker = new EventBroker();
    protected Map<String, Set<EventListener>> specifiekeListeners = new HashMap<>();
    protected LinkedList<QueueItem> queue = new LinkedList<>();
    private volatile boolean running = false;
    private Thread werkdraad;

    private EventBroker() {
    }
    private class QueueItem {
        public Event e;
        public EventPublisher source;
        public QueueItem(Event e,EventPublisher source) {
            this.e = e;
            this.source = source;
        }

    }

    public void start(){
        running = true;
        werkdraad = new Thread(this);
        werkdraad.start();

    }
    public void stop() {
        running = false;
        synchronized(queue) {
            queue.notifyAll();
        }

        try {
            werkdraad.join(); //zorg dat alles afgewerkt wordt
        } catch (InterruptedException e) {
            System.out.println("er ging iets mis in de interrupt van werkdraad");
        }
    }

    public void run(){
        while (running) {
            QueueItem qi;
            synchronized(queue) {
                while (queue.isEmpty() && running) {
                    try { queue.wait(); } //sta queue terug af
                    catch (InterruptedException e) {
                        System.out.println("mis in lus van eventbroker");
                    }
                }
                qi = queue.poll();
            }
            if (qi != null) {
                process(qi.source, qi.e);
            }
        }

        //werk de laatste nog af
        while(!queue.isEmpty()){
            QueueItem huidig = queue.poll();
            process(huidig.source,huidig.e);
        }
    }

    public static EventBroker getEventBroker() {
        return broker;
    }

    public void addEventListener(EventListener s) {
        listeners.add(s);
    }

    public void addEventListener(String type,EventListener s){
        specifiekeListeners.putIfAbsent(type, new HashSet<>());
        specifiekeListeners.get(type).add(s);
    }

    public void removeEventListener(EventListener s) {
        listeners.remove(s);
        for (Set<EventListener> luisteraars : specifiekeListeners.values()) {
            luisteraars.remove(s);
        }
    }

    void addEvent(EventPublisher source, Event e) {
        if(running){
            synchronized (queue){
                QueueItem qi = new QueueItem(e,source);
                queue.add(qi);
                queue.notify();
            }
        }

    }

    private void process(EventPublisher source, Event e) {
        for (EventListener l : listeners) {
            if (l != source) {
                l.handleEvent(e); // prevent loops !
            }
        }
        Set<EventListener> luisteraars = specifiekeListeners.get(e.getType());
        for(EventListener l: luisteraars){
            if(l != source){
                l.handleEvent(e);
            }
        }
    }
}
