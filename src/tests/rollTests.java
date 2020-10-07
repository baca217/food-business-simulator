package tests;
import rolls.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class rollTests {
    private final RollFactory testFactory = new RollFactory();

    @Test
    public void rollInstantiate()
    {
        assertEquals("EggRoll", testFactory.createRoll("egg").getClass().getSimpleName());
        assertEquals("JellyRoll", testFactory.createRoll("jelly").getClass().getSimpleName());
        assertEquals("PastryRoll", testFactory.createRoll("pastry").getClass().getSimpleName());
        assertEquals("SausageRoll", testFactory.createRoll("sausage").getClass().getSimpleName());
        assertEquals("SpringRoll", testFactory.createRoll("spring").getClass().getSimpleName());
    }
}
