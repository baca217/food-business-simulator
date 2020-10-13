//import customers.*;
import customers.BusinessCustomer;
import customers.CasualCustomer;
import customers.CateringCustomer;
import customers.Customer;
import shops.*;
import tests.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class shop_simulation {
    public static void main(String[] args)
    {
        rollTests rTest = new rollTests();
        storeTests sTest = new storeTests();
        customerTests cTest = new customerTests();

        ColoradoRollStore myStore = new ColoradoRollStore();
        List<Customer> customers = new ArrayList<>();
        List<Customer> customerTypes = Arrays.asList(new CasualCustomer(), new BusinessCustomer(), new CateringCustomer());
        int days = 30;
        int i;
        int j;
        Random rand = new Random();
        int customerAmount;
        int casualUpper = 12;
        int otherUpper = 3;
        List<Integer> bounds = Arrays.asList(casualUpper, otherUpper, otherUpper);

        //cTest.customerOrderTest();
        cTest.failOrderTest();

        /*
        rTest.rollInstantiate();
        rTest.rollDecorator();

        sTest.storeInstantiate();
        sTest.storeSell();
        sTest.storeOpen();
        sTest.noRollRefill();
        sTest.storeInput();
        sTest.storeExtras();

        myStore.startDay();

        for(i = 0; i < bounds.size(); i++) //creating random customers
        {
            int boundSize = bounds.get(i);
            Customer type = customerTypes.get(i);
            customerAmount = rand.nextInt(boundSize) + 1;
            for(j = 0; j < customerAmount; j++) //creating certain amounts of each customer
            {
                customers.add(type);
            }
        }

        for(i = 0; i < days; i++)
        {
            System.out.println("------------------------------------------------------------");
            System.out.println("day :"+ (i + 1));
            myStore.printInventory();
        }
         */
    }
}
