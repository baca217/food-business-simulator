import customers.*;
import shops.*;
import tests.*;
import initialization.*;

import java.util.*;

public class shop_simulation {
    public static void main(String[] args)
    {
        rollTests rTest = new rollTests();
        storeTests sTest = new storeTests();
        customerTests cTest = new customerTests();

        ColoradoRollStore myStore = new ColoradoRollStore();

        int i, j, days = 30, casualUpper = 12, otherUpper = 3;
        List<Integer> bounds = Arrays.asList(casualUpper, otherUpper, otherUpper);
        HashMap<String, Double> customerOrders = new HashMap<>();
        List<Customer> customers;
        InitData initializer = new InitData();
        PrintData printer = new PrintData();
        Random rand = new Random();
        HashMap<String, Integer> rollSales = initializer.rollSellsMap(myStore.menu());


        //cTest.customerOrderTest();
        //cTest.failOrderTest();
        //sTest.customerOrders();
        //cTest.testRollsList();

        /*
        rTest.rollInstantiate();
        rTest.rollDecorator();

        sTest.storeInstantiate();
        sTest.storeSell();
        sTest.storeOpen();
        sTest.noRollRefill();
        sTest.storeInput();
        sTest.storeExtras();
        */

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
                customers.remove(randNum); //remove customer from the list
                System.out.println("Customer type:"+tempCust.getClass().getSimpleName());
                myStore.rollOrders(tempCust.arriveAndOrder(myStore)); //customer came and ordered
            }
            myStore.printInventory();
        }
    }
}
