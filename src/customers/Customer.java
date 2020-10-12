package customers;
import shops.*;

import rolls.EggRoll;
import rolls.JellyRoll;
import rolls.PastryRoll;
import rolls.Roll;
import rolls.SausageRoll;
import rolls.SpringRoll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract public class Customer {
    protected RollStore myStore;

    List<String> rollOptions = new ArrayList<>() {{
        add("egg");
        add("jelly");
        add("pastry");
        add("sausage");
        add("spring");
    }};

    public void goToStore(RollStore tempStore)
    {
        if(tempStore.isStoreOpen())
        {
            myStore = tempStore;
        }
    }

    public abstract List<String> rollOrders();

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
