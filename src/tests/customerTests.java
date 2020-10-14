package tests;
import customers.*;
import org.junit.jupiter.api.Test;
import rolls.Roll;
import shops.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class customerTests
{
    ColoradoRollStore testStore = new ColoradoRollStore();
    BusinessCustomer businessCustomer = new BusinessCustomer();
    CasualCustomer casualCustomer = new CasualCustomer();
    CateringCustomer cateringCustomer = new CateringCustomer();
    int i,j;

    @Test
    public void customerOrder()
    {
        List<Customer> testCust = Arrays.asList(businessCustomer, casualCustomer, cateringCustomer);
        for(Customer customer: testCust)
        {
            System.out.println("Customer type: "+customer.getClass().getSimpleName());
            for(String order: customer.firstOrder(testStore.menu()))
            {
                System.out.println(order);
            }
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    public void orderFail()
    {
        testStore.startDay();
        List<Customer> testCust = Arrays.asList(businessCustomer, casualCustomer, cateringCustomer);
        int i, j;
        List<String> menu = testStore.menu();
        String type;

        //business test
        for(i = 0; i < 30; i++)
        {
            testStore.getRoll("egg roll");
        }
        List<String> order = businessCustomer.firstOrder(testStore.menu());
        List<String> canDo = testStore.canDoOrder(order);
        List<String> order2 = businessCustomer.secondOrder(canDo, testStore.menu(), testStore.giveInventory());
        System.out.println("business---------------------------------");
        System.out.println(order);
        System.out.println(canDo);
        System.out.println(order2);
        //casual customer test
        testStore.startDay();
        for(i = 1; i < 5; i++)
        {
            type = menu.get(i);
            for(j = 0; j < 30; j++)
            {
                testStore.getRoll(type);
            }
        }

        type = menu.get(0);
        for(i = 0; i < 29; i++)
        {
            testStore.getRoll(type);
        }
        order = casualCustomer.firstOrder(testStore.menu());
        canDo = testStore.canDoOrder(order);
        HashMap<String, Integer> temp = testStore.giveInventory();
        order2 = casualCustomer.secondOrder(canDo, testStore.menu(), temp);
        System.out.println("Casual---------------------------");
        System.out.println(order);
        System.out.println(canDo);
        System.out.println(order2);
        //catering customer test
        testStore.getRoll("egg roll");
        testStore.printInventory();
        testStore.startDay();
        for(String r: menu)
        {
            for(i = 0; i < 28; i++)
            {testStore.getRoll(r);}
        }

        order = cateringCustomer.firstOrder(menu);
        testStore.printInventory();
        System.out.println("WTF");
        canDo = testStore.canDoOrder(order);
        testStore.printInventory();
        order2 = cateringCustomer.secondOrder(canDo, menu, testStore.giveInventory());
        System.out.println("catering---------------------------");
        System.out.println(order);
        System.out.println(canDo.size());
        System.out.println(order2.size());
    }
}
