package tests;

import customers.BusinessCustomer;
import customers.Customer;
import org.junit.jupiter.api.Test;
import rolls.*;
import shops.ColoradoRollStore;
import shops.RollStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyUnitTest {
    @Test
    public void checkInstantiation(List<Customer> customers)
    {
        List<String> customerTypes = Arrays.asList("CateringCustomer", "CasualCustomer", "BusinessCustomer");
        for(Customer customer: customers)
        {
            String name = customer.getClass().getSimpleName();
            boolean found = false;
            for(String type: customerTypes)
            {
                if(name.equals(type))
                {
                    found = true;
                    break;
                }
            }
            assertEquals(true, found);
        }
        System.out.println("^^^^^Test for correct customer instantiation^^^^^");
    }

    @Test
    public void rollInstantiate()
    {
        RollFactory testFactory = new RollFactory();
        assertEquals("EggRoll", testFactory.createRoll("egg roll").getClass().getSimpleName());
        assertEquals("JellyRoll", testFactory.createRoll("jelly roll").getClass().getSimpleName());
        assertEquals("PastryRoll", testFactory.createRoll("pastry roll").getClass().getSimpleName());
        assertEquals("SausageRoll", testFactory.createRoll("sausage roll").getClass().getSimpleName());
        assertEquals("SpringRoll", testFactory.createRoll("spring roll").getClass().getSimpleName());
        System.out.println("^^^^^Roll instantiation test succeeded^^^^^^^^^^");
    }

    @Test
    public void rollDecorator(RollFactory testFactory)
    {
        Roll test = testFactory.createRoll("egg roll");
        test = new Filling(test);
        assertEquals("Egg roll, extra filling", test.getDescription());
        assertEquals(.70, test.cost());
        System.out.println("^^^^^Roll Decorator test succeeded^^^^^^^^^^");
    }

    public void checkListenerAmount(RollStore store, int customers)
    {
        assertEquals(store.support.getPropertyChangeListeners().length, customers);
        System.out.println("^^^^^PCL test for store succeeded^^^^^");
    }

    public  void checkInventory(HashMap<String, Integer> inventory)
    {
        ColoradoRollStore store = new ColoradoRollStore();
        List<String> menu = store.menu();
        for(String item: menu)
        {
            assertEquals(30, inventory.get(item));
        }
        System.out.println("^^^^^First store inventory check succeeded^^^^^");
    }

    public void storeOrder()
    {
        ColoradoRollStore tStore = new ColoradoRollStore();
        tStore.startDay();
        assertEquals(new EggRoll().getDescription(), tStore.getRoll("egg roll").getDescription());
        System.out.println("^^^^^Store ordering test passed^^^^^");
    }

    public void rollDecoratorExtension()
    {
        Roll tRoll = new PastryRoll();
        Roll dRoll = new Filling(tRoll);
        assertEquals("Pastry roll, extra filling", dRoll.getDescription());
        System.out.println("^^^^^Store decorator test passed^^^^^");
    }

    public void testStoreClosing()
    {
        ColoradoRollStore tStore = new ColoradoRollStore();
        BusinessCustomer btest = new BusinessCustomer();
        tStore.addPropertyChangeListener(btest);

        tStore.startDay();
        for(String type: tStore.menu())
        {
            for(int i = 0; i < 30; i++)
            {
                tStore.getRoll(type);
            }
        }
        tStore.rollOrders(Arrays.asList("egg roll"));
       assertEquals(false, btest.checkIfOpen());
        System.out.println("^^^^^Store closing test passed^^^^^");
    }

    public void testBusinessFailure()
    {
        BusinessCustomer bTest = new BusinessCustomer();
        ColoradoRollStore tStore = new ColoradoRollStore();
        HashMap<String, Integer> tInv = tStore.giveInventory();
        assertEquals(Arrays.asList().size(), bTest.secondOrder(new ArrayList<>(), tStore.menu(), tInv).size());
        System.out.println("^^^^^Business customer leaving test passed^^^^^");
    }

    public void testBusinessOrder()
    {
        ColoradoRollStore sTest = new ColoradoRollStore();
        BusinessCustomer bTest = new BusinessCustomer();
        List<String> list = bTest.firstOrder(sTest.menu());
        for(int i = 0; i < list.size(); i+=2)
        {
            assertEquals(list.get(i).split(",")[0], list.get(i).split(",")[0]);
        }
        System.out.println("^^^^^Business customer ordering test passed^^^^^");
    }
}
