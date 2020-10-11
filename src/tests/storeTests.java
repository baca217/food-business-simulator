package tests;
import shops.*;
import rolls.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

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
            test.rollOrder("egg roll");
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
                test.rollOrder(type);
            }
        }
        test.rollOrder("egg roll");
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
        test.rollOrder("this, is, my, order");
        test.rollOrder("");
    }

    @Test
    public void storeExtras()
    {
        System.out.println("\nstoreExtras test");

        Roll testRoll = test.rollOrder("egg roll, extra cream, extra topping, extra sauce, extra filling");
        System.out.println(testRoll.getDescription());
    }
}
