package shops;
import rolls.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class RollStore
{
    protected int noRolls;
    protected int rollAmount;
    protected List<String> rollTypes;
    protected List<String> rollExtras;
    protected HashMap<String, List<Roll>> rollInventory = new HashMap<>();
    protected RollFactory rollFactory = new RollFactory();
    protected boolean open;
    protected PropertyChangeSupport support = new PropertyChangeSupport(this);

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
        return rollInventory.get(type).size();
    }

    public void printInventory()
    {
        for(String type: rollTypes)
        {
            System.out.println(type+" roll amount: "+getInventory(type));
        }
    }

    public abstract List<Roll> rollOrders(List<String> order);
}
