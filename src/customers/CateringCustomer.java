package customers;

import shops.RollStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CateringCustomer extends Customer {
    @Override
    public List<String> firstOrder(List<String> menu)
    {
        List<String> myOrder = new ArrayList<>();
        List<String> rollTypes = new ArrayList<>();
        Random rand = new Random();
        int i = 0, j;

        while(i < 3)
        {
            int randType = rand.nextInt(menu.size());
            if(!rollTypes.contains(randType))
            {
                rollTypes.add(menu.get(randType));
                i++;
            }
        }

        for(i = 0; i < 3; i++)
        {
            String type = rollTypes.get(i);
            for(j = 0; j < 5; j++)
            {
                myOrder.add(type+addToppings());
            }
        }
        return myOrder;
    }
    @Override
    public List<String> secondOrder(List<String> canFulfill, List<String> menu, HashMap<String, Integer> inventory)
    {
        List<String> newOrder = new ArrayList<>();
        int i;

        for(String item: menu)
        {
            for(i = 0; i < inventory.get(item); i++)
            {
                newOrder.add(item+addToppings());
                if(newOrder.size() == 15)break;
            }
            if(newOrder.size() == 15)break;
        }
        return newOrder;
    }
}
