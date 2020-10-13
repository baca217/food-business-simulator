package shops;
import rolls.*;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.beans.PropertyChangeSupport;

public class ColoradoRollStore extends RollStore
{

    public ColoradoRollStore()
    {
        this.rollTypes = Arrays.asList("egg roll", "jelly roll", "pastry roll", "sausage roll", "spring roll");
        this.rollExtras = Arrays.asList("extra sauce", "extra filling", "extra topping");
        this.rollAmount = 30;
        for(String type: this.rollTypes)
        {
            this.rollInventory.put(type, new ArrayList<>());
        }
        this.noRolls = this.rollTypes.size();
        this.open = false;
    }


    public void startDay() //start day, check inventory,
    {
        int i;
        int j;
        for(i = 0; i < this.rollTypes.size(); i++)
        {
            String type = this.rollTypes.get(i);
            if(this.rollInventory.get(type).size() == 0) { //check if inventory refill is needed
                for (j = 0; j < this.rollAmount; j++) { //refill roll inventory
                    Roll tempRoll = this.rollFactory.createRoll(type);
                    this.rollInventory.get(type).add(tempRoll);
                }
                if(this.noRolls == 0)
                {
                    System.out.println("accident in start day, noRolls is 0 when it shouldn't be!!!");
                }
                this.noRolls--;
            }
            support.firePropertyChange("open", false, true);
        }
        this.open = true;
    }

    public Roll getRoll(String type)
    {
        Roll roll = null;
        if(rollInventory.get(type).size() > 0)
        {
            roll = rollInventory.get(type).get(0);
            rollInventory.get(type).remove(0);
            if(rollInventory.get(type).size() == 0)
            {
                System.out.println("Just ran out of "+type+" rolls!");
                this.noRolls++;
            }
        }
        else
        {
            System.out.println("There are no more "+type+" rolls!");
        }
        return roll;
    }

    public Roll identifyAndGetRoll(String rollName)
    {
        Roll roll = null;
        rollName = rollName.trim().toLowerCase();
        boolean found = false;
        for(String r: rollTypes)
        {
            if(r.equals(rollName))
            {
                roll = getRoll(rollName);
                break;
            }
        }
        if(roll == null)
        {
            System.out.println(rollName+" isn't a known roll!");
        }
        return roll;
    }

    public Roll rollExtra(Roll curRoll, String extra)
    {
        switch (extra.trim().toLowerCase())
        {
            case "extra sauce":
                curRoll =  new Sauce(curRoll);
                break;
            case "extra filling":
                curRoll = new Filling(curRoll);
                break;
            case "extra topping":
                curRoll = new Topping(curRoll);
                break;
            default:
                System.out.println(extra+" is not a known topping");
        }
        return curRoll;
    }

    public Roll rollOrder(String order)
    {
        String [] arrOfOrder = order.split(",");
        String rollType = arrOfOrder[0];
        Roll roll;

        if (this.noRolls == this.rollTypes.size()) //check if we need to close the store
        {
            System.out.println("The store is out of rolls! we will close.");
            this.open = false;
            support.firePropertyChange("open", false, true);
            return null;
        }

        roll = identifyAndGetRoll(rollType.split(" ")[0]);
        if(roll != null)
        {
            for (int i = 1; i < arrOfOrder.length; i++)
            {
                roll = rollExtra(roll, arrOfOrder[i]);
            }
        }
        return roll;
    }


}
