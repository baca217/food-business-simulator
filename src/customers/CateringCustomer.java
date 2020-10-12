package customers;

import shops.RollStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CateringCustomer extends Customer {

    @Override
    public List<String> rollOrders() {
        List<Integer> randomNumbers = new ArrayList<>();
        List<String> order = new ArrayList<>();
        Random rand = new Random();

        for(int i = 0; i < rollOptions.size(); i++) {
            randomNumbers.add(i);
        }
        int i = 0;
        while(i < 3) {
            int rollNum = rand.nextInt(randomNumbers.size());
            randomNumbers.remove(rollNum);
            List<String> inventory = myStore.menu();
            int zeroCount = 0;
            int fiveCount = 0;
            // Make sure the Inventory has enough items in it.
            for(String item : inventory) {
                if(myStore.getInventory(item) == 0) {
                    zeroCount++;
                }

                if(myStore.getInventory(item) >= 5) {
                    fiveCount++;
                }
            }

            if(zeroCount == inventory.size()) return null;
            int addedCount = 0;
            String individualOrder;

            if(fiveCount < 3) {
                for(String item : inventory) {
                    int itemsAvailable = myStore.getInventory(item);
                    if(itemsAvailable > 0) {
                        for(int j = 0; j < itemsAvailable; j++) {
                            if(addedCount == 15) break;
                            individualOrder = item + "," + addToppings();
                            order.add(individualOrder);
                            addedCount++;
                        }
                    }
                }

                if(addedCount == 15) break;

            }
            // THIS IS BALLS
            else if(myStore.getInventory(rollOptions.get(rollNum)) >= 5) {
                for(int j = 0; j < 5; j++) {
                    individualOrder = rollOptions.get(rollNum) + "," + addToppings();
                    order.add(individualOrder);
                }
                i++;
            }
        }

        return order;
    }
}
