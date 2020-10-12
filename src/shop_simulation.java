//import customers.*;
import customers.BusinessCustomer;
import customers.CasualCustomer;
import customers.CateringCustomer;
import customers.Customer;
import shops.*;
import tests.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class shop_simulation {
    public static void main(String[] args)
    {
        rollTests rTest = new rollTests();
        storeTests sTest = new storeTests();
        ColoradoRollStore myStore = new ColoradoRollStore();
        List<Customer> customers = new ArrayList<>();
        int days = 30;
        int i;
        int j;
        Random rand = new Random();
        int customerAmount;
        int casualUpper = 12;
        int otherUpper = 3;

        rTest.rollInstantiate();
        rTest.rollDecorator();

        sTest.storeInstantiate();
        sTest.storeSell();
        sTest.storeOpen();
        sTest.noRollRefill();
        sTest.storeInput();
        sTest.storeExtras();

        myStore.startDay();

        customerAmount  = rand.nextInt(casualUpper) + 1; //casual customer generation
        for(i = 0; i < customerAmount; i++)
        {
            customers.add(new CasualCustomer());
        }
        customerAmount = rand.nextInt(otherUpper) + 1;//business customer generation
        for(i = 0; i < customerAmount; i++)
        {
            customers.add(new BusinessCustomer());
        }
        customerAmount = rand.nextInt(otherUpper) + 1;//catering customer generation
        for(i = 0; i < customerAmount; i++)
        {
            customers.add(new CateringCustomer());
        }

        for(i = 0; i < days; i++)
        {
            System.out.println("------------------------------------------------------------");
            System.out.println("day :"+ (i + 1));
            myStore.printInventory();
        }
    }
}
