package customers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CasualCustomer extends Customer{
    @Override
    public List<String> rollOrders() {
        Random rand = new Random();
        List<String> order = new ArrayList<>();
        int rollOrderSize;
        int availableRolls = getNumberOfAvailable();
        rollOrderSize = rand.nextInt(3) + 1;

        if(availableRolls < rollOrderSize) {
            rollOrderSize = availableRolls;
        }

        int i = 0;
        while(i < rollOrderSize) {
            int rollToOrder = rand.nextInt(rollOptions.size());
            if(myStore.getInventory(rollOptions.get(rollToOrder)) > 0) {
                order.add(rollOptions.get(rollToOrder));
                i++;
            }
        }

        return order;
    }

}
