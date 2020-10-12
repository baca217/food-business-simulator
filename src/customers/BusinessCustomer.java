package customers;

import rolls.Roll;
import shops.RollStore;

import java.util.ArrayList;
import java.util.List;

public class BusinessCustomer extends Customer {
    @Override
    public List<String> rollOrders() {
        List<String> order = new ArrayList<>();

        for(String roll : rollOptions) {
            for(int i = 0; i < 2; i++) {
                String individualOrder = roll + "," + addToppings();
                order.add(individualOrder);
            }
        }

        return order;
    }

}
