package tests;
import rolls.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class rollTests {
    private final rollFactory testFactory = new rollFactory();

    @Test
    public void rollInstantiate()
    {
        assertEquals("eggRoll", testFactory.createRoll("egg").getClass().getSimpleName());
        assertEquals("jellyRoll", testFactory.createRoll("jelly").getClass().getSimpleName());
        assertEquals("pastryRoll", testFactory.createRoll("pastry").getClass().getSimpleName());
        assertEquals("sausageRoll", testFactory.createRoll("sausage").getClass().getSimpleName());
        assertEquals("springRoll", testFactory.createRoll("spring").getClass().getSimpleName());
    }
}
