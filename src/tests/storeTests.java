package tests;
import shops.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class storeTests
{
    private final ColoradoRollStore test = new ColoradoRollStore();

    @Test
    public void storeInstantiate()
    {
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
        int i;
        test.startDay();
        for(i = 30; i > -1; i--)
        {
            assertEquals(i, test.getInventory("egg"));
            test.sellRoll("egg");
        }
    }
}
