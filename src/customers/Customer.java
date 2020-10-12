package customers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract public class Customer {

    public abstract List<String> rollOrders(List<String> rollOptions);

    public String addToppings(){
        Random rand = new Random();
        StringBuilder order = new StringBuilder();
        int randomNum1 = rand.nextInt(100);
        int randomNum2 = rand.nextInt(100);
        int randomNum3 = rand.nextInt(100);
        int randAmount;

        if(randomNum1 < 45) {
            randAmount = rand.nextInt(3);
            order.append("extra sauce,".repeat(randAmount));
        }

        if(randomNum2 > 35 && randomNum2 < 75) {
            order.append("extra fillings,");
        }

        if(randomNum3 > 65) {
            randAmount = rand.nextInt(2);
            order.append("extra toppings,".repeat(randAmount));
        }
        order.deleteCharAt(order.length() - 1);
        return order.toString();
    }
}
