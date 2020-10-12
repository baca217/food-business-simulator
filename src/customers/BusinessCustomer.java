package customers;

import rolls.Roll;

import java.util.ArrayList;
import java.util.List;

public class BusinessCustomer extends Customer {
    @Override
    public List<Roll> rollOrders() {
        List<Roll> order = new ArrayList<>();

        for(Roll roll : rollOptions) {
            for(int i = 0; i < 2; i++) {
                order.add(roll);
            }
        }

        return order;
    }
}
