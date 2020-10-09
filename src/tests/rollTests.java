package tests;
import rolls.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class rollTests {
    private final RollFactory testFactory = new RollFactory();

    @Test
    public void rollInstantiate()
    {
        System.out.println("rollInstantiate test\n");
        assertEquals("EggRoll", testFactory.createRoll("egg").getClass().getSimpleName());
        assertEquals("JellyRoll", testFactory.createRoll("jelly").getClass().getSimpleName());
        assertEquals("PastryRoll", testFactory.createRoll("pastry").getClass().getSimpleName());
        assertEquals("SausageRoll", testFactory.createRoll("sausage").getClass().getSimpleName());
        assertEquals("SpringRoll", testFactory.createRoll("spring").getClass().getSimpleName());
    }

    @Test
    public void rollDecorator()
    {
        System.out.println("rollDecorator test\n");
        Roll test = testFactory.createRoll("egg");
        test = new Filling(test);
        assertEquals("Egg roll, extra filling", test.getDescription());
        assertEquals(.70, test.cost());
    }
}
