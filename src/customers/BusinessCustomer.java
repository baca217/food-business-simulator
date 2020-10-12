package customers;

import rolls.Roll;

import java.util.ArrayList;
import java.util.List;

public class BusinessCustomer extends Customer {
    @Override
    public List<String> rollOrders() {
        List<String> order = new ArrayList<>();

        for(String roll : rollOptions) {
            for(int i = 0; i < 2; i++) {
                order.add(roll);
            }
        }

        return order;
    }
}
