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
                String individualOrder = roll + "," + addToppings();
                order.add(individualOrder);
            }
        }

        return order;
    }

    @Override
    public void placeOrder()
    {
        List<String> myOrder = rollOrders();
        List<Roll> rollBox = new ArrayList<>();
        Roll tempRoll;
        for(String rollOrder: myOrder)
        {
            tempRoll = myStore.rollOrder(rollOrder);
            if(tempRoll == null)
            {
                System.out.println("No rolls, not buying, leaving");
                return;
            }
            rollBox.add(tempRoll);
        }
    }
}
