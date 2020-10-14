import customers.*;
import rolls.Roll;
import shops.*;
import tests.*;
import initialization.*;

import java.util.*;

public class shop_simulation {
    public static void main(String[] args)
    {
        //rollTests rTest = new rollTests();
        //storeTests sTest = new storeTests();
        //customerTests cTest = new customerTests();

        ColoradoRollStore myStore = new ColoradoRollStore();

        int i, j, days = 30, casualUpper = 12, otherUpper = 3;
        List<Integer> bounds = Arrays.asList(casualUpper, otherUpper, otherUpper);
        HashMap<String, Double> customerOrders = new HashMap<>();
        List<Customer> customers;
        InitData initializer = new InitData();
        PrintData printer = new PrintData();
        Random rand = new Random();

        for(i = 0; i < days; i++)
        {
            Customer tempCust = null;
            int randNum = 0;
            System.out.println("------------------------------------------------------------");
            System.out.println("day :"+ (i + 1));
            customers = initializer.randomCustomerList(bounds);
            for(Customer customer: customers)
            {
                myStore.addPropertyChangeListener(customer);
            }

            myStore.startDay();
            for(j= 0; j < customers.size(); j++)
            {
                randNum = rand.nextInt(customers.size());
                tempCust = customers.get(randNum);//get random customer
                String name = tempCust.getClass().getSimpleName();
                customers.remove(randNum); //remove customer from the list
                List<Roll> rolls = myStore.serviceCustomer(tempCust);
                System.out.println("<<<<Customer type:"+tempCust.getClass().getSimpleName()+">>>>");
                tempCust.receive(rolls);
            }
            myStore.printInventory();
            myStore.printDailySales();
            myStore.printDailyOutages();
        }
        myStore.printEndSales();
        myStore.printRollsSold();
        myStore.printTotalOutages();
    }
}
