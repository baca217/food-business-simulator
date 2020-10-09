package shops;
import rolls.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ColoradoRollStore extends RollStore
{
    private HashMap<String, List<Roll>> rollInventory = new HashMap<>();
    private String[] rollTypes = {"egg", "jelly", "pastry", "sausage", "spring"};
    private RollFactory rollFactory = new RollFactory();
    private int rollAmount = 30;
    private int noRolls;
    private boolean open;

    public ColoradoRollStore()
    {
        for(int i = 0; i < this.rollTypes.length; i++)
        {
            String type = this.rollTypes[i];
            this.rollInventory.put(type, new ArrayList<Roll>());
        }
        this.noRolls = 5;
        this.open = false;
    }


    public void startDay() //start day, check inventory,
    {
        int i;
        int j;
        for(i = 0; i < this.rollTypes.length; i++)
        {
            String type = this.rollTypes[i];
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
        }
        this.open = true;
    }
    public void sellRoll(String type)
    {
        if(rollInventory.get(type).size() > 0)
        {
            rollInventory.get(type).remove(0);
            if(rollInventory.get(type).size() == 0)
            {
                System.out.println("Just ran out of "+type+" rolls!");
                this.noRolls++;
                if(this.noRolls == 5)
                {
                    System.out.println("Store is out of bagels! Closing shop");
                    this.open = false;
                }
            }
        }
        else
        {
            System.out.println("There are no more "+type+" rolls!");
        }
    }

    public int getInventory(String type)
    {
        return rollInventory.get(type).size();
    }

    public boolean isStoreOpen()
    {
        return  this.open;
    }

    public String[] menu()
    {
        return this.rollTypes;
    }
}
