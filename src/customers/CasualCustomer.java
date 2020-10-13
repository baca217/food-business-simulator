package customers;

import shops.RollStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CasualCustomer extends Customer{
    @Override
    protected List<String> rollOrders(RollStore myStore) {
        Random rand = new Random();
        List<String> rollOptions = myStore.menu();
        List<String> order = new ArrayList<>();
        int rollOrderSize;
        int availableRolls = getNumberOfAvailable(myStore);
        rollOrderSize = rand.nextInt(3) + 1;

        if(availableRolls < rollOrderSize) { //number of rolls available is less than amount that we want
            System.out.println("casual customer reduced order from "+rollOrderSize+" to "+availableRolls);
            rollOrderSize = availableRolls;
        }

        int i = 0;
        while(i < rollOrderSize) {
            int rollToOrder = rand.nextInt(rollOptions.size());
            if(myStore.getInventory(rollOptions.get(rollToOrder)) > 0) { //checking if random roll has any inventory available
                order.add(rollOptions.get(rollToOrder) + addToppings());
                i++;
            }
        }

        return order;
    }

}
