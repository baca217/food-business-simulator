package shops;
import customers.BusinessCustomer;
import customers.CasualCustomer;
import customers.CateringCustomer;
import customers.Customer;
import rolls.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class RollStore
{
    protected int noRolls;
    protected int rollAmount;
    protected int totalOutages;
    protected List<String> rollTypes;
    protected List<String> rollExtras;
    protected HashMap<String, Integer> inventory = new HashMap<>();
    protected  HashMap<String, Double> daily = new HashMap<>();
    protected HashMap<String, Double> end = new HashMap<>();
    protected  HashMap<String, Integer> rollsSold = new HashMap<>();
    protected HashMap<String, Integer> dailyOutages = new HashMap<>();
    protected List<String> customerTypes = new ArrayList<>();
    protected RollFactory rollFactory = new RollFactory();
    protected boolean open;
    public PropertyChangeSupport support = new PropertyChangeSupport(this);

    public RollStore()
    {
        List<Customer> temp = Arrays.asList(new BusinessCustomer(), new CateringCustomer(), new CasualCustomer());
        for(Customer cust: temp)
        {
            String name = cust.getClass().getSimpleName();
            daily.put(name, 0.0);
            end.put(name, 0.0);
            dailyOutages.put(name, 0);
            customerTypes.add(name);
        }
        totalOutages = 0;
    }

    protected abstract void startDay();

    protected abstract Roll getRoll(String type);

    public boolean isStoreOpen()
    {
        return  this.open;
    }

    public List<String> menu()
    {
        return this.rollTypes;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl)
    {
        support.addPropertyChangeListener(pcl);
    }

    public int getInventory(String type)
    {
        return inventory.get(type);
    }

    public void printInventory()
    {
        System.out.println("*********STORE INVENTORY:*************");
        for(String type: rollTypes)
        {
            System.out.println(type+" amount: "+getInventory(type));
        }
    }

    public abstract List<Roll> rollOrders(List<String> order);

    public List<String> canDoOrder(List<String> order)
    {
        HashMap<String, Integer> temp = new HashMap<>(inventory);
        List<String> canDo = new ArrayList<>();

        for(String part: order)
        {
            String rollType = part.split(",")[0].trim().toLowerCase();
            if(temp.get(rollType) - 1 > -1) //enough stock to fulfill order
            {
                temp.put(rollType, temp.get(rollType)-1);
                canDo.add(part);
            }
        }
        return canDo;
    }

    public HashMap<String, Integer> giveInventory()
    {
        return new HashMap<>(this.inventory);
    }

    public List<Roll> serviceCustomer(Customer customer)
    {
        List<String> order = customer.firstOrder(this.menu());
        String simpName = customer.getClass().getSimpleName();
        int len = order.size();
        List<String> posbOrder = canDoOrder(order);
        int poLen = posbOrder.size();
        List<Roll> rolls;

        if(poLen == len)
        {
            rolls = this.rollOrders(order);
        }
        else
        {
            totalOutages++;
            dailyOutages.put(simpName, dailyOutages.get(simpName)+1 );
            order = customer.secondOrder(posbOrder, this.menu(), new HashMap<>(inventory));
            rolls = this.rollOrders(order);
        }
        updateSales(rolls, customer.getClass().getSimpleName());
        updateRollsSold(rolls);
        return rolls;
    }

    private double orderCost(List<Roll> rollOrder)
    {
        double cost = 0;
        for(Roll roll: rollOrder)
        {
            cost += roll.cost();
        }
        return cost;
    }

    private void updateSales(List<Roll> rolls, String name)
    {
        double cost = orderCost(rolls);
        this.daily.put(name, this.daily.get(name)+cost);
        this.end.put(name, this.end.get(name)+cost);
    }

    public void printDailySales()
    {
        System.out.println("***********DAILY SPENDING:***********");
        for(String type: customerTypes)
        {
            double total = daily.get(type);
            System.out.println(type+" spent $"+total);
        }
    }

    public void printEndSales()
    {
        System.out.println("***********SPENDING AT END OF 30 DAYS***********");
        for(String type: customerTypes)
        {
            double total = end.get(type);
            System.out.println(type+" spent $"+total);
        }
    }

    public void resetDaily()
    {
        for(String type: customerTypes)
        {
            daily.put(type, 0.0);
        }
    }

    public void updateRollsSold(List<Roll> rolls)
    {
        for(Roll roll: rolls)
        {
            String type = roll.getRollType().trim().toLowerCase();
            rollsSold.put(type, rollsSold.get(type) + 1);
        }
    }

    public void printRollsSold()
    {
        System.out.println("******ROLLS SOLD AT END OF 30 DAYS******");
        int total = 0;
        for(String roll: rollTypes)
        {
            int sold = rollsSold.get(roll);
            total += sold;
            System.out.println(roll+" sold: "+sold);
        }
        System.out.println("Total rolls sold: "+total);
    }

    protected void resetDailyOutages()
    {
        for(String type: customerTypes)
        {
            dailyOutages.put(type, 0);
        }
    }

    public void printDailyOutages()
    {
        System.out.println("********DAILY OUTAGES********");
        for(String type: customerTypes)
        {
            System.out.println(type+" "+dailyOutages.get(type)+" outages");
        }
    }

    public void printTotalOutages()
    {
        System.out.println("********TOTAL OUTAGES********");
        System.out.println("total outages: "+totalOutages);
    }
}
