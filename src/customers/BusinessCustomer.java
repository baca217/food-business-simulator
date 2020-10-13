package customers;

import rolls.Roll;
import shops.RollStore;

import java.util.ArrayList;
import java.util.List;

public class BusinessCustomer extends Customer {
    @Override
    protected List<String> rollOrders(RollStore myStore) {
        List<String> order = new ArrayList<>();

        if(getNumberOfAvailable(myStore) == -1) return new ArrayList<>();

        for(String roll : myStore.menu()) {
            for(int i = 0; i < 2; i++) {
                String individualOrder = roll + addToppings();
                order.add(individualOrder);
            }
        }

        return order;
    }

    @Override
    public int getNumberOfAvailable(RollStore myStore) {
        int count = 0;
        List<String> menu = myStore.menu();
        for (String s : menu) {
            if(myStore.getInventory(s) < 2) {
                return -1;
            }

            count++;
        }

        return count;
    }

}
