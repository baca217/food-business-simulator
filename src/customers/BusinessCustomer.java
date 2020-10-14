package customers;

import rolls.Roll;
import shops.RollStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BusinessCustomer extends Customer {
    @Override
    public List<String> firstOrder(List<String> menu)
    {
        List<String> myOrder = new ArrayList<>();
        for(String item: menu)
        {
            myOrder.add(item+addToppings());
            myOrder.add(item+addToppings());
        }
        return myOrder;
    }
    @Override
    public List<String> secondOrder(List<String> canFulfill, List<String> menu, HashMap<String, Integer> inventory)
    {
        System.out.println("Business customer ordered nothing!!!");
        return new ArrayList<>();
    }

}
