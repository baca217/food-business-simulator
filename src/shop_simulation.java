import customers.*;
import rolls.*;
import shops.*;
import tests.*;
import initialization.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class shop_simulation {
    public static void main(String[] args) throws FileNotFoundException {
        MyUnitTest uTest = new MyUnitTest();

        ColoradoRollStore myStore = new ColoradoRollStore();

        int i, j, days = 30, casualUpper = 12, otherUpper = 3;
        List<Integer> bounds = Arrays.asList(casualUpper, otherUpper, otherUpper);
        List<Customer> customers;
        InitData initializer = new InitData();
        Random rand = new Random();

        //PrintStream out = new PrintStream(new FileOutputStream("45then60.out"));
        //System.setOut(out);
        uTest.rollInstantiate();
        uTest.rollDecorator(new RollFactory());
        uTest.storeOrder();
        uTest.rollDecoratorExtension();
        for(i = 0; i < days; i++)
        {
            Customer tempCust = null;
            int randNum = 0;
            System.out.println("------------------------------------------------------------");
            System.out.println("day :"+ (i + 1));
            customers = initializer.randomCustomerList(bounds);
            uTest.checkInstantiation(customers);
            for(Customer customer: customers)
            {
                myStore.addPropertyChangeListener(customer);
            }

            myStore.startDay();

            if(i  == 0) {
                uTest.checkListenerAmount(myStore, customers.size());
                uTest.checkInventory(myStore.giveInventory());
            }

            for(j= 0; j < customers.size(); j++)
            {
                randNum = rand.nextInt(customers.size());
                tempCust = customers.get(randNum);//get random customer
                String name = tempCust.getClass().getSimpleName();
                customers.remove(randNum); //remove customer from the list
                if(tempCust.checkIfOpen())
                {
                    List<Roll> rolls = myStore.serviceCustomer(tempCust);
                    System.out.println("<<<<Customer type:"+tempCust.getClass().getSimpleName()+">>>>");
                    tempCust.receive(rolls);
                }
                else
                {
                    System.out.println(tempCust.getClass()+" didn't order because of the store closure");
                }
            }
            myStore.printInventory();
            myStore.printDailySales();
            myStore.printDailyOutages();
        }
        myStore.printEndSales();
        myStore.printRollsSold();
        myStore.printTotalOutages();
        System.out.println("****END OF SIMULATION*****");
        uTest.testStoreClosing();
        uTest.testBusinessFailure();
        uTest.testBusinessOrder();
    }
}
