package customers;
import shops.*;

import rolls.EggRoll;
import rolls.JellyRoll;
import rolls.PastryRoll;
import rolls.Roll;
import rolls.SausageRoll;
import rolls.SpringRoll;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract public class Customer implements PropertyChangeListener
{
    protected boolean isStoreOpen = false;
    List<Roll> myRolls;

    public int getNumberOfAvailable(RollStore myStore) {
        int count = 0;
        List<String> menu = myStore.menu();
        for (String s : menu) {
            count += myStore.getInventory(s);
        }

        return count;
    }

    protected abstract List<String> rollOrders(RollStore myStore);

    public List<String> arriveAndOrder(RollStore myStore)
    {
        if(isStoreOpen)
        {
            return rollOrders(myStore);
        }
        else
        {
            return null;
        }
    }

    public void receiveOrder(List<Roll> rollOrder)
    {
        myRolls = rollOrder;
    }

    public String addToppings(){
        Random rand = new Random();
        StringBuilder order = new StringBuilder();
        int randomNum1 = rand.nextInt(100);
        int randomNum2 = rand.nextInt(100);
        int randomNum3 = rand.nextInt(100);
        int randAmount;

        if(randomNum1 < 45) {
            randAmount = rand.nextInt(3);
            order.append(", extra sauce".repeat(randAmount));
        }

        if(randomNum2 > 35 && randomNum2 < 75) {
            order.append(", extra filling");
        }

        if(randomNum3 > 65) {
            randAmount = rand.nextInt(2);
            order.append(", extra topping".repeat(randAmount));
        }
        return order.toString();
    }

    public void propertyChange(PropertyChangeEvent evt)
    {
        switch (evt.getPropertyName())
        {
            case "open":
                isStoreOpen = (boolean) evt.getNewValue();
                break;
            default:
                System.out.println(evt.getPropertyName()+" is an unsupported event");
        }
    }

}
