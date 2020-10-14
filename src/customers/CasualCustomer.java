package customers;

import shops.RollStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CasualCustomer extends Customer{
    int numRolls;
    List<String> myOrder;
    @Override
    public List<String> firstOrder(List<String> menu)
    {
        int i, j;
        Random rand = new Random();
        this.numRolls = rand.nextInt(3) + 1;
        myOrder = new ArrayList<>();

        for(i = 0; i < this.numRolls; i++)
        {
            j = rand.nextInt(menu.size()); //random item from menu
            String item = menu.get(j); //get random item from menu
            myOrder.add(item+addToppings());
        }
        return myOrder;
    }
    @Override
    public List<String> secondOrder(List<String> canFulfill, List<String> menu, HashMap<String, Integer> inventory)
    {
        int needToOrder = myOrder.size() - canFulfill.size(); // difference between items we wanted and can be fulfilled
        int totRolls = 0;
        Random rand = new Random();

        for(String order: canFulfill) //remove stuff that we already ordered from inventory
        {
            String type = order.split(",")[0].trim().toLowerCase();
            inventory.put(type, inventory.get(type) - 1);
        }
        for(String roll: menu) //get total amount of rolls we can buy
        {
            if(inventory.get(roll) > 0) {
                totRolls += inventory.get(roll);
            }
        }
        if( totRolls < needToOrder) //is there less rolls than what we want to order?
        {
            needToOrder = totRolls; //reduce amount of rolls we can order
        }
        while(needToOrder > 0)
        {
            int randType = rand.nextInt(menu.size()); //random number for picking random item
            String type = menu.get(randType); //pick random item
            if(inventory.get(type) > 0) //check if that type is available
            {
                canFulfill.add(type+addToppings()); //add it to our old order
                needToOrder--;
            }
        }

        return canFulfill;
    }

}
