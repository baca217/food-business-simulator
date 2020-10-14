package tests;
import customers.BusinessCustomer;
import shops.*;
import rolls.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import javax.swing.event.ListDataEvent;
import java.util.Arrays;
import java.util.List;

public class storeTests
{
    private final ColoradoRollStore test = new ColoradoRollStore();

    @Test
    public void storeInstantiate()
    {

        System.out.println("storeInstantiate test");
        test.startDay();
        assertEquals(30, test.getInventory("egg"));
        assertEquals(30, test.getInventory("jelly"));
        assertEquals(30, test.getInventory("pastry"));
        assertEquals(30, test.getInventory("sausage"));
        assertEquals(30, test.getInventory("spring"));
    }

    @Test
    public void storeSell()
    {
        System.out.println("\nstoreSell test");
        int i;
        test.startDay();
        for(i = 0; i < 30; i++)
        {
            assertEquals(30 - i, test.getInventory("egg"));
            test.rollOrders(Arrays.asList("egg roll"));
        }
    }

    @Test
    public void storeOpen()
    {
        System.out.println("\nstoreOpen test");
        int i;
        int j;
        List<String> menu = test.menu();
        test.startDay();
        assertEquals(true, test.isStoreOpen());
        for(j = 0; j < 5; j++)
        {
            String type = menu.get(j);
            for (i = 0; i < 30; i++)
            {
                test.rollOrders(Arrays.asList(type));
            }
        }
        test.rollOrders(Arrays.asList("egg roll"));
        assertEquals(false, test.isStoreOpen());
    }

    @Test
    public void noRollRefill()
    {
        System.out.println("\nnoRollRefill test");
        int i;
        test.startDay(); //create thirty of each roll
        assertEquals(30, test.getInventory("egg"));
        test.getRoll("egg"); //sell roll
        test.startDay();
        assertEquals(29, test.getInventory("egg")); //ensure it wasn't refilled
    }

    @Test
    public void storeInput()
    {
        System.out.println("\nstoreInput test");
        test.rollOrders(Arrays.asList("this, is, my, order"));
        test.rollOrders(Arrays.asList(""));
    }

    @Test
    public void storeExtras()
    {
        System.out.println("\nstoreExtras test");
        List<Roll> testRoll = test.rollOrders(Arrays.asList("egg roll, extra cream, extra topping, extra sauce, extra filling"));
        for(Roll thisRoll: testRoll)
        {
            System.out.println(thisRoll.getDescription());
        }
    }

    @Test
    public void customerInput()
    {
        System.out.println("business input");
        BusinessCustomer businessCustomer = new BusinessCustomer();
        test.addPropertyChangeListener(businessCustomer);

        test.startDay();
        businessCustomer.receive(test.serviceCustomer(businessCustomer));
        test.printInventory();
    }
}
