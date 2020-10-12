package customers;

import rolls.Roll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Casual extends Customer{

    @Override
    public List<Roll> rollOrders() {
        Random rand = new Random();
        List<Roll> order = new ArrayList<>();
        int rollOrderSize = rand.nextInt(3);
        for(int i = 0; i < rollOrderSize; i++) {
            int rollToOrder = rand.nextInt(rollOptions.size());
            order.add(rollOptions.get(rollToOrder));
        }
        
        return order;
    }
}
