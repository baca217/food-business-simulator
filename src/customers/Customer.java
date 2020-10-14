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
import java.util.HashMap;
import java.util.List;
import java.util.Random;

abstract public class Customer implements PropertyChangeListener
{
    protected boolean isStoreOpen = false;
    List<Roll> myRolls = new ArrayList<>();

    public abstract List<String>  firstOrder(List<String> menu);
    public abstract List<String> secondOrder(List<String> canFulfill, List<String> menu, HashMap<String, Integer> inventory);

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

    public void receive(List<Roll> storeRolls)
    {
        HashMap<String, Integer> temp = new HashMap<String, Integer>();
        List<String> rollTypes = new ArrayList<>();
        myRolls = storeRolls;
        double cost = 0;

        for(Roll roll: myRolls)
        {
            String type = roll.getRollType();
            cost += roll.cost();
            if(!temp.containsKey(type))
            {
                temp.put(type, 1);
                rollTypes.add(type);
            }
            else {
                    temp.put(type, temp.get(type)+1 );
            }
        }
        for(String type: rollTypes)
        {
            System.out.println(type+" amount: "+temp.get(type));
        }
        System.out.println("Total cost: $"+cost);
    }

    public boolean checkIfOpen()
    {
        return isStoreOpen;
    }

    public double totalCost()
    {
        double cost = 0;
        for(Roll roll: myRolls)
        {
            cost += roll.cost();
        }
        return cost;
    }
}
