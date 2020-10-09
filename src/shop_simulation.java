//import customers.*;
import rolls.*;
//import shops.*;
import tests.*;

public class shop_simulation {
    public static void main(String[] args)
    {
        rollTests rTest = new rollTests();
        storeTests sTest = new storeTests();

        rTest.rollInstantiate();
        rTest.rollDecorator();

        sTest.storeInstantiate();
        sTest.storeSell();
        sTest.storeOpen();
        sTest.noRollRefill();
    }
}
