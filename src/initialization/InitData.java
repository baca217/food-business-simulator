package initialization;
import customers.*;

import java.util.*;

public class InitData
{
    public List<Customer> randomCustomerList(List<Integer> bounds)
    {
        List<Customer> customerTypes = Arrays.asList(new CasualCustomer(), new BusinessCustomer(), new CateringCustomer());
        int customerAmount;
        List<Customer> temp = new ArrayList<>();
        Random rand = new Random();
        int i, j;

        for(i = 0; i < bounds.size(); i++) //creating random customers
        {
            int boundSize = bounds.get(i);
            Customer type = customerTypes.get(i);
            customerAmount = rand.nextInt(boundSize) + 1;
            for(j = 0; j < customerAmount; j++) //creating certain amounts of each customer
            {
                temp.add(type);
            }
        }
        return temp;
    }


    public HashMap<String, Integer> rollSellsMap(List<String> menu)
    {
        HashMap<String, Integer> tempMap = new HashMap<>();
        for(String item: menu)
        {
            tempMap.put(item, 0);
        }
        return tempMap;
    }
}
