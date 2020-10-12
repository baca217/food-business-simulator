package customers;

import rolls.Roll;
import shops.RollStore;

import java.util.ArrayList;
import java.util.List;

public class BusinessCustomer extends Customer {
    @Override
    public List<String> rollOrders() {
        List<String> order = new ArrayList<>();

        if(getNumberOfAvailable() == -1) return null;

        for(String roll : rollOptions) {
            for(int i = 0; i < 2; i++) {
                String individualOrder = roll + "," + addToppings();
                order.add(individualOrder);
            }
        }

        return order;
    }

    @Override
    public int getNumberOfAvailable() {
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
