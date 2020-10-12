package customers;

import rolls.Roll;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CateringCustomer extends Customer {

    @Override
    public List<String> rollOrders(List<String> rollOptions) {
        List<Integer> randomNumbers = new ArrayList<>();
        List<String> order = new ArrayList<>();
        Random rand = new Random();

        for(int i = 0; i < rollOptions.size(); i++) {
            randomNumbers.add(i);
        }

        for(int i = 0; i < 3; i++) {
            int rollNum = rand.nextInt(randomNumbers.size());
            randomNumbers.remove(rollNum);
            for(int j = 0; j < 5; j++) {
                String individualOrder = rollOptions.get(rollNum) + "," + addToppings();
                order.add(individualOrder);
            }
        }

        return order;
    }
}
