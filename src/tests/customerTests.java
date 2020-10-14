package tests;
import customers.*;
import org.junit.jupiter.api.Test;
import rolls.Roll;
import shops.*;

import java.util.List;


public class customerTests
{
    ColoradoRollStore testStore = new ColoradoRollStore();
    BusinessCustomer businessCustomer = new BusinessCustomer();
    CasualCustomer casualCustomer = new CasualCustomer();
    CateringCustomer cateringCustomer = new CateringCustomer();
    int i,j;

    @Test
    public void testRollsList()
    {
        testStore.addPropertyChangeListener(businessCustomer);
        testStore.startDay();

        businessCustomer.arriveAndOrder(testStore);
        for(Roll roll: businessCustomer.rollTypesBought())
        {
            System.out.println(roll.getDescription()+"-------------");
            System.out.println(roll.getRollType());
        }
    }

    @Test
    public void customerOrderTest()
    {
        testStore.addPropertyChangeListener(businessCustomer);
        testStore.addPropertyChangeListener(casualCustomer);
        testStore.addPropertyChangeListener(cateringCustomer);

        testStore.startDay();

        System.out.println("business customer:--------------------");
        for(String order: businessCustomer.arriveAndOrder(testStore))
        {
            System.out.println(order);
        }

        System.out.println("catering customer:--------------------");
        for(String order: cateringCustomer.arriveAndOrder(testStore))
        {
            System.out.println(order);
        }

        System.out.println("casual customer:--------------------");
        for(String order: casualCustomer.arriveAndOrder(testStore))
        {
            System.out.println(order);
        }
    }

    @Test
    public void failOrderTest()
    {
        testStore.addPropertyChangeListener(businessCustomer);
        testStore.addPropertyChangeListener(casualCustomer);
        testStore.addPropertyChangeListener(cateringCustomer);

        //business customer test
        testStore.startDay();
        for(i = 0; i < 30; i++)
        {
            testStore.getRoll("egg roll");
        }
        System.out.println("business customer:--------------------");
        for(String order: businessCustomer.arriveAndOrder(testStore))
        {
            System.out.println(order);
        }
        //casual customer test
        testStore.startDay();
        List<String> tempMenu = testStore.menu();
        for(i = 0; i < tempMenu.size() - 1; i++)
        {
            for(j = 0; j  < 30; j++)
            {
                testStore.getRoll(tempMenu.get(i));
            }
        }

        j = tempMenu.size()-1;
        for(i = 0; i < 29; i++)
        {
            testStore.getRoll(tempMenu.get(j));
        }

        System.out.println("casual customer:--------------------");
        for(String order: casualCustomer.arriveAndOrder(testStore))
        {
            System.out.println(order);
        }

        //cater customer test
        testStore.startDay();
        for(i = 0; i < tempMenu.size(); i++)
        {
            for(j = 0; j  < 26; j++)
            {
                testStore.getRoll(tempMenu.get(i));
            }
        }

        System.out.println("cater customer:--------------------");
        for(String order: cateringCustomer.arriveAndOrder(testStore))
        {
            System.out.println(order);
        }
    }
}
