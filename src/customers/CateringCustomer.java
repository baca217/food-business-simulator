package customers;

import shops.RollStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CateringCustomer extends Customer {

    @Override
    public List<String> rollOrders(RollStore myStore) {
        List<String> order = new ArrayList<>();
        Random rand = new Random();
        List<String> menu = myStore.menu();
        int menuSize = menu.size();
        int i = 0;
        int zeroCount = 0;
        int fiveCount = 0;

        // Make sure the Inventory has enough items in it.
        for(String item : menu) {
            if(myStore.getInventory(item) == 0) {
                zeroCount++;
            }

            if(myStore.getInventory(item) >= 5) {
                fiveCount++;
            }
        }

        if(zeroCount == menu.size()) return null; //no roll inventory in store
        String individualOrder;

        if(fiveCount < 3) //wasn't 3 or more roll types with inventory greater than 5
        {
            for(String item : menu)
            {
                int itemsAvailable = myStore.getInventory(item);
                if(itemsAvailable > 0) { //there is a roll amount available
                    for(int j = 0; j < itemsAvailable; j++)
                    {
                        individualOrder = item + addToppings();
                        order.add(individualOrder);
                        if(order.size() == 15) break; //fulfilled order with rolls going through menu
                    }
                }
                if(order.size() == 15) break; //fulfilled order with rolls going through menu
            }
        }
        else
        {
            while(i < 3)
            {
                int rollNum = rand.nextInt(menuSize);
                if (myStore.getInventory(menu.get(rollNum)) >= 5)
                {
                    for (int j = 0; j < 5; j++) {
                        individualOrder = menu.get(rollNum) + addToppings();
                        order.add(individualOrder);
                    }
                    i++;
                }
            }
        }

        return order;
    }
}
