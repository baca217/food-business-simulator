package customers;

import rolls.Roll;
import shops.RollStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CasualCustomer extends Customer{

    @Override
    public void placeOrder() {

    }

    @Override
    public List<String> rollOrders(RollStore rollStore) {
        Random rand = new Random();
        List<String> order = new ArrayList<>();

        int rollOrderSize = rand.nextInt(3) + 1;
        int i = 0;
        while(i < rollOrderSize) {
            int rollToOrder = rand.nextInt(rollOptions.size());
            if(rollStore.getInventory(rollOptions.get(rollToOrder)) > 0) {
                order.add(rollOptions.get(rollToOrder));
                i++;
            }
        }

        return order;
    }
}
