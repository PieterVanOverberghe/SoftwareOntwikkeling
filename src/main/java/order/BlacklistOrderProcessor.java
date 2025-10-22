
package order;

import eventbroker.EventBroker;

import java.util.HashSet;
import java.util.Set;


public class BlacklistOrderProcessor extends OrderProcessor {

    private Set<String> blacklist;
    private int i = 0;
    
    protected BlacklistOrderProcessor(){
        super();

    }

    public static BlacklistOrderProcessor create(){
        BlacklistOrderProcessor bp = new BlacklistOrderProcessor();
        bp.blacklist = new HashSet<String>();
        bp.blacklist.add("Jan");
        EventBroker.getEventBroker().addEventListener(OrderEvent.TYPE_ORDER, bp);
        return bp;
    }
    
    @Override
    protected void processOrder(OrderEvent order){
        // ignore blacklisted customers
        if(blacklist.contains(order.getCustomer())){
            i++;
            if(i%100==0){
            System.out.println("Order of customer "+order.getCustomer()+" discarded");
            }
            return;
        }
        
        super.processOrder(order);
    }
    
}
