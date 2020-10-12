package customers;

import shops.RollStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CateringCustomer extends Customer {

    @Override
    public List<String> rollOrders(RollStore rollStore) {
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
            List<String> inventory = rollStore.menu();
            int zeroCount = 0;

            for(String item : inventory) {
                if(rollStore.getInventory(item) == 0) {
                    zeroCount++;
                }
            }

            if(zeroCount == )

            if(rollStore.getInventory(rollOptions.get(rollNum)) >= 5) {
                for(int j = 0; j < 5; j++) {
                    String individualOrder = rollOptions.get(rollNum) + "," + addToppings();
                    order.add(individualOrder);
                }
                i++;
            }
        }

        return order;
    }
}
