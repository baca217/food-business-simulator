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
        if(availableRolls < 3) {
            rollOrderSize = availableRolls;
        } else {
            rollOrderSize = rand.nextInt(3) + 1;
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

    public int getNumberOfAvailable() {
        int count = 0;
        List<String> menu = myStore.menu();
        for (String s : menu) {
            count += myStore.getInventory(s);
        }

        return count;
    }
}
