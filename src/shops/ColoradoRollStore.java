package shops;
import rolls.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ColoradoRollStore extends RollStore
{

    public ColoradoRollStore()
    {
        super();
        HashMap<String, Integer> temp = new HashMap<>();
        this.rollTypes = Arrays.asList("egg roll", "jelly roll", "pastry roll", "sausage roll", "spring roll");
        this.rollExtras = Arrays.asList("extra sauce", "extra filling", "extra topping");
        this.rollAmount = 30;
        for(String extra: rollExtras)
        {
            temp.put(extra, 0);
        }
        for(String type: this.rollTypes)
        {
            this.inventory.put(type, -1);
        }
        this.noRolls = this.rollTypes.size();
        this.open = false;
        for(String item: rollTypes)
        {
            rollsSold.put(item, 0);
        }
    }


    public void startDay() //start day, check inventory,
    {
        int i;
        resetDaily();
        resetDailyOutages();
        for(i = 0; i < this.rollTypes.size(); i++)
        {
            String type = this.rollTypes.get(i);
            if(this.inventory.get(type) == -1) { //check if inventory refill is needed
                this.inventory.put(type, 30);
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
        if(inventory.get(type) > 0)
        {
            roll = rollFactory.createRoll(type);
            this.inventory.put(type, this.inventory.get(type) - 1);
            if(inventory.get(type) == 0)
            {
                System.out.println("Just ran out of "+type+" rolls!");
                this.noRolls++;
                this.inventory.put(type, -1);
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
                found  = true;
                break;
            }
        }
        if(!found)
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

    public List<Roll> rollOrders(List<String> orderList)
    {
        List<Roll> rolls = new ArrayList<>();
        for(String order: orderList)
        {
            String[] arrOfOrder = order.split(",");
            String rollType = arrOfOrder[0];
            Roll roll;

            if (this.noRolls == this.rollTypes.size()) //check if we need to close the store
            {
                System.out.println("The store is out of rolls! we will close.");
                this.open = false;
                support.firePropertyChange("open", false, true);
                return rolls;
            }

            roll = identifyAndGetRoll(rollType);
            if (roll != null) {
                for (int i = 1; i < arrOfOrder.length; i++) {
                    roll = rollExtra(roll, arrOfOrder[i]);
                }
                rolls.add(roll);
            }
        }
        return rolls;
    }


}
